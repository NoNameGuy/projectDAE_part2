/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.EventDTO;
import entity.Event;
import entity.Participant;
import entity.Responsible;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author UTIL
 */
@Stateless
@Path("/events")
public class EventBean {

    @PersistenceContext
    private EntityManager em;

    /*public List<String> list() {
        
     }*/
    public void createEvent(int id, Date date, String name, String type, String local, String responsible_username)
            throws EntityAlreadyExistsException, EntityDoesNotExistsException, MyConstraintViolationException {
        try {

            if (em.find(Event.class, id) != null) {
                throw new EntityAlreadyExistsException("A Event with that id already exists.");
            }
            Responsible responsible = em.find(Responsible.class, responsible_username);
            if (responsible == null) {
                throw new EntityDoesNotExistsException("There is no Responsible with that id.");
            }

            Event event = new Event(id, date, name, type, local, responsible);

            responsible.addEvent(event);

            //System.out.println(event.toString());
            em.persist(event);

        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void updateEvent(int id, Date date, String name, String type, String local, String responsible_username) throws EntityDoesNotExistsException {

        try {
            Event event = em.find(Event.class, id);

            Responsible responsible = em.find(Responsible.class, responsible_username);
            if (responsible == null) {
                throw new EntityDoesNotExistsException("There is no Responsible with that id.");
            }

            if (event == null) {
            }
            event.setId(id);
            event.setDate(date);
            event.setName(name);
            event.setType(type);
            event.setLocal(local);
            event.setResponsible(responsible);

            em.merge(event);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }

    }


    public boolean isOpenInscriptions(int eventId) throws EntityDoesNotExistsException {
        try {
            Event event = em.find(Event.class, eventId);
            if (event == null) {
                throw new EntityDoesNotExistsException("There is no event with that id.");
            }
            if (event.isOpenInscriptions()) {
                return true;
            }

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return false;
    }

    public void openInscriptions(int eventId) throws EntityDoesNotExistsException {
        try {
            Event event = em.find(Event.class, eventId);
            if (event == null) {
                throw new EntityDoesNotExistsException("There is no event with that id.");
            }
            event.setOpenInscriptions(true);
            em.merge(event);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void closeInscriptions(int eventId) throws EntityDoesNotExistsException {
        try {
            Event event = em.find(Event.class, eventId);
            if (event == null) {
                throw new EntityDoesNotExistsException("There is no event with that id.");
            }
            event.setOpenInscriptions(false);
            em.merge(event);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeEvent(int id) throws EntityDoesNotExistsException {
        try {
            Event event = em.find(Event.class, id);
            if (event == null) {
                throw new EntityDoesNotExistsException("There is no event with that id.");
            }

            em.remove(event);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<EventDTO> getAllEvents() {
        List<Event> events = (List<Event>) em.createNamedQuery("getAllEvents").getResultList();
        return eventsToDTOs(events);
    }

    public List<EventDTO> getParticipantEvents(String username) throws EntityDoesNotExistsException {
        try {
            Participant participant = em.find(Participant.class, username);
            if (participant == null) {
                throw new EntityDoesNotExistsException("Participant does not exists.");
            }
            return eventsToDTOs(participant.getEvents());
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<EventDTO> getResponsibleEvents(String username) throws EntityDoesNotExistsException {
        try {
            Responsible responsible = em.find(Responsible.class, username);
            if (responsible == null) {
                throw new EntityDoesNotExistsException("Responsible does not exists.");
            }
            return eventsToDTOs(responsible.getEvents());
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    EventDTO eventToDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getDate(),
                event.getName(),
                event.getType(),
                event.getLocal(),
                event.getResponsible().getUsername(),
                event.getResponsible().getName(),
                event.isOpenInscriptions());
    }

    List<EventDTO> eventsToDTOs(List<Event> events) {
        List<EventDTO> dtos = new ArrayList<>();
        for (Event e : events) {
            dtos.add(eventToDTO(e));
        }
        return dtos;
    }

}

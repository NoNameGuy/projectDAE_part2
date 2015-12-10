/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.ParticipantDTO;
import entity.Event;
import entity.Participant;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.ParticipantEnrolledException;
import exceptions.ParticipantNotEnrolledException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author UTIL
 */
@Stateless
public class ParticipantBean {

    @PersistenceContext(unitName = "ProjectDAE-ejbPU")
    private EntityManager em;

    //Create Participant
    public void createParticipant(int id, String password, String name, String email)
            throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Participant.class, id) != null) {
                throw new EntityAlreadyExistsException("A Participant with that usermane already exists.");
            }
            em.persist(new Participant(id, password, name, email));
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //Participant Update
    public void updateParticipant(int id, String password, String name, String email)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Participant participant = em.find(Participant.class, id);
            if (participant == null) {
                throw new EntityDoesNotExistsException("There is no participant with that username.");
            }
            participant.setPassword(password);
            participant.setName(name);
            participant.setEmail(email);
            em.merge(participant);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //remove Participant
    public void removeParticipant(int id)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Participant participant = em.find(Participant.class, id);
            if (participant == null) {
                throw new EntityDoesNotExistsException("There is no participant with that username.");
            }
            em.remove(participant);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //Verify if participant exists
    public boolean existeParticipant(String username) {
        try {
            return em.find(Participant.class, username) != null;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    

    public void enrollParticipant(int id, int id_event)
            throws EntityDoesNotExistsException, ParticipantEnrolledException {
        try {
            Event event = em.find(Event.class, id_event);
            if (event == null) {
                throw new EntityDoesNotExistsException("There is no event with that id.");
            }

            Participant participant = em.find(Participant.class, id);
            if (participant == null) {
                throw new EntityDoesNotExistsException("There is no participant with that username.");
            }
            if (event.getParticipants().contains(participant)) {
                throw new ParticipantEnrolledException("Participant is already enrolled in that event.");
            }
            
            participant.addEvent(event);
            event.addParticipant(participant);
            
        } catch (EntityDoesNotExistsException | ParticipantEnrolledException e ) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void unrollParticipant(int id, int id_event)
            throws EntityDoesNotExistsException, ParticipantNotEnrolledException {
        try {
            Event event = em.find(Event.class, id_event);
            if (event == null) {
                throw new EntityDoesNotExistsException("There is no event with that id.");
            }

            Participant participant = em.find(Participant.class, id);
            if (participant == null) {
                throw new EntityDoesNotExistsException("There is no participant with that username.");
            }
            
            if (!event.getParticipants().contains(participant)) {
                throw new ParticipantNotEnrolledException("Participant is not enrolled in that event.");
            }
            
            participant.removeEvent(event);
            event.removeParticipant(participant);
            
        } catch (EntityDoesNotExistsException | ParticipantNotEnrolledException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<ParticipantDTO> getEnrolledParticipants(int id_event) throws EntityDoesNotExistsException{
        try {
            Event event = em.find(Event.class, id_event);
            if( event == null){
                throw new EntityDoesNotExistsException("There is no event with that id.");
            }            
            List<Participant> participants = (List<Participant>) event.getParticipants();
            return participantsToDTOs(participants);
        } catch (EntityDoesNotExistsException e) {
            throw e;             
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<ParticipantDTO> getUnrolledParticipants(int id_event) throws EntityDoesNotExistsException{
        try {
            Event event = em.find(Event.class, id_event);
            if( event == null){
                throw new EntityDoesNotExistsException("There is no event with that id.");
            }            
            List<Participant> participants = em.createNamedQuery("getAllParticipants").getResultList();
                    
            List<Participant> enrolled = em.find(Event.class, id_event).getParticipants();
            participants.removeAll(enrolled);
            return participantsToDTOs(participants);
        } catch (EntityDoesNotExistsException e) {
            throw e;             
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<ParticipantDTO> getAllParticipants() {
        List<Participant> participants = (List<Participant>) em.createNamedQuery("getAllParticipants").getResultList();
        return participantsToDTOs(participants);
    }
    
    

    ParticipantDTO participantToDTO(Participant participant) {
        return new ParticipantDTO(
                participant.getId(),
                participant.getPassword(),
                participant.getName(),
                participant.getEmail());
    }

    List<ParticipantDTO> participantsToDTOs(List<Participant> participants) {
        List<ParticipantDTO> dtos = new ArrayList<>();
        for (Participant p : participants) {
            dtos.add(participantToDTO(p));
        }
        return dtos;
    }
}

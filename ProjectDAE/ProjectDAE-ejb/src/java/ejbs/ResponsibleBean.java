/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.ResponsibleDTO;
import entity.Event;
import entity.Responsible;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
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
@Path("/responsibles")
public class ResponsibleBean {

    @PersistenceContext(unitName = "ProjectDAE-ejbPU")
    private EntityManager em;

    public List<Event> listEvents() {
        return em.createNamedQuery("listEvents").getResultList();
    }

    //Create Responsible
    public void createResponsible(int id, String password, String name, String email)
            throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Responsible.class, id) != null) {
                throw new EntityAlreadyExistsException("A Responsible with that usermane already exists.");
            }
            em.persist(new Responsible(id, password, name, email));
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //Responsible Update
    public void updateResponsible(int id, String password, String name, String email)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Responsible responsible = em.find(Responsible.class, id);
            if (responsible == null) {
                throw new EntityDoesNotExistsException("There is no responsible with that username.");
            }
            responsible.setPassword(password);
            responsible.setName(name);
            responsible.setEmail(email);
            em.merge(responsible);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //remove Responsible
    public void removeResponsible(int id)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Responsible responsible = em.find(Responsible.class, id);
            if (responsible == null) {
                throw new EntityDoesNotExistsException("There is no participant with that username.");
            }
            em.remove(responsible);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //Verify if responsible exists
    public boolean existeResponsible(String id) {
        try {
            return em.find(Responsible.class, id) != null;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void createEvent() {
        try {
            // Event event = new Event();
            // entityManager.persist(event);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeEvent() {
        try {

            //Event event = entityManager.find(Event.class, this);
            //entityManager.remove(event);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void openEventAttendance() {

    }

    public void closeEventAttendance() {

    }

    public void enrollResponsible() {

    }

    public void unenrollResponsible() {

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<ResponsibleDTO> getAllResponsibles() {
        List<Responsible> responsibles = (List<Responsible>) em.createNamedQuery("getAllResponsibles").getResultList();
        return responsiblesToDTOs(responsibles);
    }

    ResponsibleDTO responsibleToDTO(Responsible responsible) {
        return new ResponsibleDTO(
                responsible.getId(),
                responsible.getPassword(),
                responsible.getName(),
                responsible.getEmail());
    }

    List<ResponsibleDTO> responsiblesToDTOs(List<Responsible> responsibles) {
        List<ResponsibleDTO> dtos = new ArrayList<>();
        for (Responsible r : responsibles) {
            dtos.add(responsibleToDTO(r));
        }
        return dtos;
    }
}

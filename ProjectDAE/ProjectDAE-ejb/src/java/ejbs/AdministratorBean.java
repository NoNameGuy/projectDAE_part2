/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.AdministratorDTO;
import entity.Administrator;
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
 * @author paulovieira
 */
@Stateless
@Path("/administrators")
public class AdministratorBean {

    @PersistenceContext(unitName = "ProjectDAE-ejbPU")
    private EntityManager em;
    
    //Create Admin

    public void createAdmininstrator(String username, String password, String name, String email) 
        throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if(em.find(Administrator.class, username) != null){
                throw new EntityAlreadyExistsException("A Administrator with that usermane already exists.");
            }
            em.persist(new Administrator(username, password, name, email));
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));  
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //Admin Update
    
    public void updateAdministrator(String username, String name, String email, String password)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Administrator administrator = em.find(Administrator.class, username);
            if (administrator == null) {
                throw new EntityDoesNotExistsException("There is no adminstrator with that username.");
            }
            administrator.setPassword(password);
            administrator.setName(name);
            administrator.setEmail(email);
            em.merge(administrator);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));  
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //remove admin
    
    public void removeAdministrator(String username) throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Administrator administrator = em.find(Administrator.class, username);
            if (administrator == null) {
                throw new EntityDoesNotExistsException("There is no adminstrator with that username.");
            }
            em.remove(administrator);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));  
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    //Verify if admin exists
    
    public boolean existeAdministrator(String username) {
        try {
            return (em.find(Administrator.class, username) != null);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Path("all") 
    public List<AdministratorDTO> getAllAdministrators() {
        List<Administrator> administrators = (List<Administrator>) em.createNamedQuery("getAllAdministrators").getResultList();
        return administratorsToDTOs(administrators);
    }
    
    
    AdministratorDTO administratorToDTO(Administrator administrator) {
        return new AdministratorDTO(
                administrator.getUsername(),
                administrator.getPassword(),
                administrator.getName(),
                administrator.getEmail());
    }

    List<AdministratorDTO> administratorsToDTOs(List<Administrator> administrators) {
        List<AdministratorDTO> dtos = new ArrayList<>();
        for (Administrator a : administrators) {
            dtos.add(administratorToDTO(a));
        }
        return dtos;
    }



}

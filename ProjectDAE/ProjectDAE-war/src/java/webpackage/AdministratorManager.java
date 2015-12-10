/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpackage;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import dtos.AdministratorDTO;
import dtos.EventDTO;
import dtos.ParticipantDTO;
import dtos.ResponsibleDTO;
import dtos.SubjectDTO;
import ejbs.AdministratorBean;
import ejbs.EventBean;
import ejbs.ParticipantBean;
import ejbs.ResponsibleBean;
import ejbs.SubjectBean;
import exceptions.EntityDoesNotExistsException;
import exceptions.ParticipantEnrolledException;
import exceptions.ParticipantNotEnrolledException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

/**
 *
 * @author paulovieira
 */
@ManagedBean
@SessionScoped
public class AdministratorManager {

    @EJB
    private AdministratorBean administratorBean;
    @EJB
    private ResponsibleBean responsibleBean;
    @EJB
    private ParticipantBean participantBean;
    @EJB
    private EventBean eventBean;
    @EJB
    private SubjectBean subjectBean;

    private AdministratorDTO newAdministrator;
    private AdministratorDTO currentAdministrator;
    private ResponsibleDTO newResponsible;
    private ResponsibleDTO currentResponsible;
    private ParticipantDTO newParticipant;
    private ParticipantDTO currentParticipant;
    private List<ParticipantDTO> listParticipants;
    private EventDTO newEvent;
    private EventDTO currentEvent;
    private UIComponent component;

    /**
     * Creates a new instance of AdministratorManager
     */
    public AdministratorManager() {
        newAdministrator = new AdministratorDTO();
        newResponsible = new ResponsibleDTO();
        newParticipant = new ParticipantDTO();
        newEvent = new EventDTO();
    }

    //////////////////////////// Administrator \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String createAdmininstrator() {

        try {
            administratorBean.createAdmininstrator(
                    newAdministrator.getId(),
                    newAdministrator.getPassword(),
                    newAdministrator.getName(),
                    newAdministrator.getEmail());
            newAdministrator.reset();

            return "AdminPage?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin_administrator_create?faces-redirect=true";
    }

    public String updateAdministrator() {
        try {
            administratorBean.updateAdministrator(
                    currentAdministrator.getId(),
                    currentAdministrator.getName(),
                    currentAdministrator.getEmail(),
                    currentAdministrator.getPassword());
            return "AdminPage?faces-redirect=true";
        } catch (Exception e) {
            logger.warning("Problem updating user in method updateUser().");
        }
        return "admin_administrator_update?faces-redirect=true";
    }

    public void removeAdministrator(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("administratorID");
            int id = Integer.parseInt(param.getValue().toString());
            administratorBean.removeAdministrator(id);
        } catch (Exception e) {
            logger.warning("Problem removing user in method removeUser().");
        }
    }

    public List<AdministratorDTO> getAllAdministrators() {
        return administratorBean.getAllAdministrators();
    }

    //////////////////////////// Responsible \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String createResponsible() {

        try {
            responsibleBean.createResponsible(
                    newResponsible.getId(),
                    newResponsible.getPassword(),
                    newResponsible.getName(),
                    newResponsible.getEmail());
            newResponsible.reset();
            return "AdminPage?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin_responsible_create?faces-redirect=true";
    }

    public String updateResponsible() {
        try {
            responsibleBean.updateResponsible(
                    currentResponsible.getId(),
                    currentResponsible.getPassword(),
                    currentResponsible.getName(),
                    currentResponsible.getEmail());
            currentResponsible.reset();
            return "AdminPage?faces-redirect=true";
        } catch (Exception e) {
            logger.warning("Problem updating user in method updateUser().");
        }
        return "admin_responsible_update?faces-redirect=true";
    }

    public void removeResponsible(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("responsibleID");
            int id = Integer.parseInt(param.getValue().toString());
            responsibleBean.removeResponsible(id);
        } catch (Exception e) {
            logger.warning("Problem removing user in method removeUser().");
        }
    }

    public List<ResponsibleDTO> getAllResponsibles() {
        return responsibleBean.getAllResponsibles();
    }

    public List<EventDTO> getCurrentResponsibleEvents() {
        try {
            return eventBean.getResponsibleEvents(currentResponsible.getId());
        } catch (Exception e) {
            return null;
        }
    }

    //////////////////////////// Participant \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String createParticipant() {

        try {
            participantBean.createParticipant(
                    newParticipant.getId(),
                    newParticipant.getPassword(),
                    newParticipant.getName(),
                    newParticipant.getEmail());
            newParticipant.reset();
            return "AdminPage?faces-redirect=true";
        } catch (Exception e) {
            logger.warning("Problem updating user in method createParticipant().");
        }
        return "admin_participant_create?faces-redirect=true";
    }

    public String updateParticipant() {
        try {
            participantBean.updateParticipant(
                    currentParticipant.getId(),
                    currentParticipant.getPassword(),
                    currentParticipant.getName(),
                    currentParticipant.getEmail());
            currentParticipant.reset();
            return "AdminPage?faces-redirect=true";
        } catch (Exception e) {
            logger.warning("Problem updating user in method updateParticipant().");
        }
        return "admin_participant_update?faces-redirect=true";
    }

    public void removeParticipant(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("participantID");
            int id = Integer.parseInt(param.getValue().toString());
            participantBean.removeParticipant(id);
        } catch (Exception e) {
            logger.warning("Problem removing user in method removeUser().");
        }
    }
    
    public void printToScreen() {
        
            System.out.println("participant: " + listParticipants);
            //System.out.println("event: " + currentEvent.getName());
    }

    public List<ParticipantDTO> getAllParticipants() {
        return participantBean.getAllParticipants();

    }

    public List<EventDTO> getCurrentParticipantEvents() {
        try {
            return eventBean.getParticipantEvents(currentParticipant.getId());
        } catch (Exception e) {
            return null;
        }
    }

    //////////////////////////// Event \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    /*public void enrollParticipant() throws EntityDoesNotExistsException {
        if( eventBean.isOpenInscriptions(currentEvent.getId()))
            eventBean.enrollParticipant(currentEvent.getId(), currentParticipant.getId());
    }
    
    public void unEnrollParticipant(int eventId, int participantId) throws EntityDoesNotExistsException {
        if( eventBean.isOpenInscriptions(eventId))
            eventBean.unEnrollParticipant(eventId, participantId);
    }*/
    
    public void openInscription(ActionEvent event) throws EntityDoesNotExistsException {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("eventID");
            int id = Integer.parseInt(param.getValue().toString());
            eventBean.openInscriptions(id);
        } catch (Exception e) {
            logger.warning("Problem in method openInscription().");
        }
    }
    
    public void closeInscription(ActionEvent event) throws EntityDoesNotExistsException {
       try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("eventID");
            int id = Integer.parseInt(param.getValue().toString());
            eventBean.closeInscriptions(id);
        } catch (Exception e) {
            logger.warning("Problem in method closeInscription().");
        }
    }
    
    public String createEvent() {

        try {
            eventBean.createEvent(
                    newEvent.getId(),
                    newEvent.getDate(),
                    newEvent.getName(),
                    newEvent.getType(),
                    newEvent.getLocal(),
                    newEvent.getResponsible_id());
            newEvent.reset();
            return "AdminPage?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "create_event?faces-redirect=true";
    }

    public void removeEvent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("eventID");
            int id = Integer.parseInt(param.getValue().toString());
            eventBean.removeEvent(id);
        } catch (Exception e) {
            logger.warning("Problem removing user in method removeUser().");
        }
    }

    public String updateEvent() {
        try {
            eventBean.updateEvent(
                    currentEvent.getId(),
                    currentEvent.getDate(),
                    currentEvent.getName(),
                    currentEvent.getType(),
                    currentEvent.getLocal(),
                    currentEvent.getResponsible_id());

            return "AdminPage?faces-redirect=true";
        } catch (Exception e) {
            logger.warning("Problem updating user in method updateEvent().");
        }
        return "update_event?faces-redirect=true";
    }

    public List<EventDTO> getAllEvents() {
        return eventBean.getAllEvents();
    }
    
    public List<ParticipantDTO> getEnrolledParticipants() throws EntityDoesNotExistsException {
            return participantBean.getEnrolledParticipants(currentEvent.getId());
    }
    
    public List<ParticipantDTO> getUnrolledParticipants() throws EntityDoesNotExistsException {
        return participantBean.getUnrolledParticipants(currentEvent.getId()); 
            
    }
    
    public void enrollParticipants(ActionEvent event) throws EntityDoesNotExistsException, ParticipantEnrolledException {
        
        try{
            UIParameter param = (UIParameter) event.getComponent().findComponent("participantId");
            int id = Integer.parseInt(param.getValue().toString());
            //System.out.println("Participant Id: " + currentParticipant.getId() + "Event Id: " + currentEvent.getId());
            participantBean.enrollParticipant(id, currentEvent.getId());
        } catch (Exception e) {
            logger.warning("Problem enrolling participant in method enrollParticipants().");
        }
        
    }
    
    public void unrollParticipants(ActionEvent event) throws EntityDoesNotExistsException, ParticipantNotEnrolledException {
        try{
            UIParameter param = (UIParameter) event.getComponent().findComponent("participantId");
            int id = Integer.parseInt(param.getValue().toString());
            participantBean.unrollParticipant(id, 1);
        } catch (Exception e) {
            logger.warning("Problem enrolling participant in method enrollParticipants().");
        }
    }
    
    //////////////////////////// Subjects \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    
    public List<SubjectDTO> getAllSubjects() {
        return subjectBean.getAllSubjects();
    }
    
    
    
    public AdministratorBean getAdministratorBean() {
        return administratorBean;
    }

    public void setAdministratorBean(AdministratorBean administratorBean) {
        this.administratorBean = administratorBean;
    }

    public ResponsibleBean getResponsibleBean() {
        return responsibleBean;
    }

    public void setResponsibleBean(ResponsibleBean responsibleBean) {
        this.responsibleBean = responsibleBean;
    }

    public ParticipantBean getParticipantBean() {
        return participantBean;
    }

    public void setParticipantBean(ParticipantBean participantBean) {
        this.participantBean = participantBean;
    }

    public AdministratorDTO getNewAdministrator() {
        return newAdministrator;
    }

    public void setNewAdministrator(AdministratorDTO newAdministrator) {
        this.newAdministrator = newAdministrator;
    }

    public AdministratorDTO getCurrentAdministrator() {
        return currentAdministrator;
    }

    public void setCurrentAdministrator(AdministratorDTO currentAdministrator) {
        this.currentAdministrator = currentAdministrator;
    }

    public ResponsibleDTO getNewResponsible() {
        return newResponsible;
    }

    public void setNewResponsible(ResponsibleDTO newResponsible) {
        this.newResponsible = newResponsible;
    }

    public ResponsibleDTO getCurrentResponsible() {
        return currentResponsible;
    }

    public void setCurrentResponsible(ResponsibleDTO currentResponsible) {
        this.currentResponsible = currentResponsible;
    }

    public ParticipantDTO getNewParticipant() {
        return newParticipant;
    }

    public void setNewParticipant(ParticipantDTO newParticipant) {
        this.newParticipant = newParticipant;
    }

    public ParticipantDTO getCurrentParticipant() {
        return currentParticipant;
    }

    public void setCurrentParticipant(ParticipantDTO currentParticipant) {
        this.currentParticipant = currentParticipant;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public EventBean getEventBean() {
        return eventBean;
    }

    public void setEventBean(EventBean eventBean) {
        this.eventBean = eventBean;
    }

    public SubjectBean getSubjectBean() {
        return subjectBean;
    }

    public void setSubjectBean(SubjectBean subjectBean) {
        this.subjectBean = subjectBean;
    }

    public EventDTO getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(EventDTO newEvent) {
        this.newEvent = newEvent;
    }

    public EventDTO getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(EventDTO currentEvent) {
        this.currentEvent = currentEvent;
    }

    public List<ParticipantDTO> getListParticipants() {
        return listParticipants;
    }

    public void setListParticipants(List<ParticipantDTO> listParticipants) {
        this.listParticipants = listParticipants;
    }





    
    

}

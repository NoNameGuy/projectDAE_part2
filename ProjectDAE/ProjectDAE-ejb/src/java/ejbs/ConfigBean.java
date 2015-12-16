/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author paulovieira
 */
@Startup
@Singleton
public class ConfigBean {
    
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
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   
   @PostConstruct
   public void populateBD() {
       
       try {
            // Create Administrator
            System.out.println("Create Administrator");
            administratorBean.createAdmininstrator("administrator1", "administrator1", "Francisco1 Fernandes", "Admistrator1@DAE.pt");
            administratorBean.createAdmininstrator("administrator2", "administrator2", "Paulo Vieira", "Admistrator2@DAE.pt");
            
            // Create Responsible
            System.out.println("Create Responsible");
            responsibleBean.createResponsible("responsible1", "responsible1","Ricardo Martinho",  "Responsible1@DAE.pt");
            responsibleBean.createResponsible("responsible2", "responsible2", "Carlos Grilo", "Responsible2@DAE.pt");
            responsibleBean.createResponsible("responsible3", "responsible3", "responsible3", "Responsible3@DAE.pt");
            responsibleBean.createResponsible("responsible4", "responsible4", "responsible4", "Responsible4@DAE.pt");
            
            // Create Participant
            System.out.println("Create Participant");
            participantBean.createParticipant("participant1", "participant1", "participant1", "Participant1@DAE.pt");
            participantBean.createParticipant("participant2", "participant2", "participant2", "Participant2@DAE.pt");
            participantBean.createParticipant("participant3", "participant3", "participant3", "Participant3@DAE.pt");
            participantBean.createParticipant("participant4", "participant4", "participant4", "Participant4@DAE.pt");
            participantBean.createParticipant("participant5", "participant5", "participant5", "Participant5@DAE.pt");
            participantBean.createParticipant("participant6", "participant6", "participant6", "Participant6@DAE.pt");
            participantBean.createParticipant("participant7", "participant7", "participant7", "Participant7@DAE.pt");
            participantBean.createParticipant("participant8", "participant8", "participant8", "Participant8@DAE.pt");
            
            // Create Event
            System.out.println("Create Event");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            eventBean.createEvent(1, formatter.parse("21/03/2015"), "Evento 1", "Aula", "ESTG", "responsible1");
            eventBean.createEvent(2, formatter.parse("05/05/2016"), "Evento 2", "Aula", "ESTG", "responsible2");
            eventBean.createEvent(3, formatter.parse("07/10/2016"), "Evento 3", "Aula", "ESTG", "responsible4");
            eventBean.createEvent(4, formatter.parse("18/02/2017"), "Evento 4", "Aula", "ESTG", "responsible2");
            
            participantBean.enrollParticipant("participant1", 1);
            participantBean.enrollParticipant("participant2", 1);
            participantBean.enrollParticipant("participant8", 1);
            participantBean.enrollParticipant("participant3", 1);
            participantBean.enrollParticipant("participant2", 2);
            participantBean.enrollParticipant("participant8", 2);
            
            // Create Subject
            System.out.println("Create Subject");
            subjectBean.createSubject(1, "IEI", 1, "2015/2016");
            subjectBean.createSubject(2, "TC", 1, "2015/2016");
            subjectBean.createSubject(3, "PA", 2, "2015/2016");
            subjectBean.createSubject(4, "DAE", 3, "2015/2016");
            subjectBean.createSubject(5, "DAD", 3, "2015/2016");
            
            
            
       } catch (Exception e){
           
           System.err.println("Error: " + e.getMessage());
           
       }
   }
}

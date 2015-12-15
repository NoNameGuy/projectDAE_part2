/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entity.Event;
import entity.Participant;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author franc
 */
@Stateless
public class EventParticipantBean {

    @PersistenceContext
    private EntityManager em;
    
    public void registerPresence(int eventId, String participantUsername) throws EJBException{
        try {
            Event ev = em.find(Event.class, eventId);
            Participant p = em.find(Participant.class, participantUsername);
            ev.addParticipant(p);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}

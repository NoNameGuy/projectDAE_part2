/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author franc
 */
@Entity
public class EventParticipant implements Serializable {

    @Id
    private long id_event;
    @Id
    private int id_participant;
    
    private boolean presence;

    public EventParticipant() {
        
    }

    public EventParticipant(long id_event, int id_participant, boolean presence) {
        this.id_event = id_event;
        this.id_participant = id_participant;
        this.presence = presence;
    }

    public long getId_event() {
        return id_event;
    }

    public void setId_event(long id_event) {
        this.id_event = id_event;
    }

    public int getId_participant() {
        return id_participant;
    }

    public void setId_participant(int id_participant) {
        this.id_participant = id_participant;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }
    
    
    
    



    
}

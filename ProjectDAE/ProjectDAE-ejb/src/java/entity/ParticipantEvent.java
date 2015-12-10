/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author franc
 */
@Entity
public class ParticipantEvent implements Serializable {

    @Id
    private int event_id;
    @Id
    private int participant_id;

    private boolean present;

    public ParticipantEvent() {
    }

    public ParticipantEvent(int event_id, int participant_id, boolean present) {
        this.event_id = event_id;
        this.participant_id = participant_id;
        this.present = present;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author paulovieira
 */
@Entity
@Table(name = "EVENTS", uniqueConstraints
        = @UniqueConstraint(columnNames = {"NAME", "RESPONSIBLE_ID"}))
@NamedQueries({
    @NamedQuery(name = "getAllEvents",
            query = "SELECT e FROM Event e ORDER BY e.date")
    })
public class Event implements Serializable {
    
    @Id
    private int id;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private String local;
    

    @ManyToMany
    @JoinTable(name = "EVENT_PARTICIPANT",
            joinColumns
            = @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "PARTICIPANT_ID", referencedColumnName = "ID"))
    private List<Participant> participants;
    
    @ManyToOne
    @JoinColumn(name = "RESPONSIBLE_ID")
    @NotNull
    private Responsible responsible;
    private boolean openInscriptions;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Event() {
        this.participants = new LinkedList<>();
    }

    public Event(int id, Date date, String name, String type, String local, Responsible responsible) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.type = type;
        this.local = local;
        this.participants = new LinkedList<>();
        this.responsible = responsible;
        this.openInscriptions = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
    
    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
    
    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    public Responsible getResponsible() {
        return responsible;
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }
    
    public boolean isOpenInscriptions() {
        return openInscriptions;
    }

    public void setOpenInscriptions(boolean openInscriptions) {
        this.openInscriptions = openInscriptions;
    }
    
    public String toString() {
        return "Administrator{" + "username=" + id + ", date=" + date +
                ", name=" + name + ", type=" + type + ", local=" + local + 
                ", responsible=" + responsible.toString() +'}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author paulovieira
 */
@Entity 
@Table(name = "RESPONSIBLES")
@NamedQueries({
    @NamedQuery(name = "getAllResponsibles",
            query = "SELECT r FROM Responsible r ORDER BY r.name")
})

public class Responsible extends User implements Serializable {
    
    @OneToMany(mappedBy = "responsible", cascade = CascadeType.REMOVE)
    private List<Event> events;
    
    public Responsible() {
        events = new LinkedList<>();
    }
 
    public Responsible(int id, String password, String name, String email) {
        super(id, password, name, email);
        events = new LinkedList<>();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
    public void addEvent(Event event) {
        events.add(event);
    }
    
    public void removeEvent(Event event) {
        events.remove(event);
    }
    
    @Override
    public String toString() {
        return "Responsible{" + "username=" + id + ", password=" + password + ", name=" + name + ", email=" + email + '}';
    }
}

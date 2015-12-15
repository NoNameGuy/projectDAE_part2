/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author franc
 */
@XmlRootElement(name = "Event") 
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDTO implements Serializable {
    
    private int id;
    private Date date;
    private String name;
    private String type;
    private String local;
    private String responsible_username;
    private String responsible_name;
    private boolean openInscriptions;
    
    public EventDTO() {
        
    }

    public EventDTO(int id, Date date, String name, String type, String local, String responsible_username, String responsible_name, boolean openInscriptions) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.type = type;
        this.local = local;
        this.responsible_username = responsible_username;
        this.responsible_name = responsible_name;
        this.openInscriptions = openInscriptions;


    }
    
    public void reset(){
        this.id = 0;
        this.date = null;
        this.name = null;
        this.type = null;
        this.local = null;
        this.responsible_username = null;
        this.responsible_name = null;
     
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

    public String getResponsible_username() {
        return responsible_username;
    }

    public void setResponsible_username(String responsible_username) {
        this.responsible_username = responsible_username;
    }

    public String getResponsible_name() {
        return responsible_name;
    }

    public void setResponsible_name(String responsible_name) {
        this.responsible_name = responsible_name;
    }

    public boolean isOpenInscriptions() {
        return openInscriptions;
    }

    public void setOpenInscriptions(boolean openInscrptions) {
        this.openInscriptions = openInscrptions;
    }
    
    
    
    
    
    
    
}

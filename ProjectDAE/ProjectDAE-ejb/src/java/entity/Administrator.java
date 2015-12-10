/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author paulovieira
 */
@Entity
@Table(name = "ADIMISTRATORS")
    @NamedQuery(name = "getAllAdministrators", query = "SELECT a FROM Administrator a ORDER BY a.name")

public class Administrator extends User implements Serializable {
    
    public Administrator() {

    }
    
    public Administrator(int id, String password, String name, String email) {
        super(id, password, name, email);
    }
    
    @Override
    public String toString() {
        return "Administrator{" + "username=" + id + ", password=" + password + ", name=" + name + ", email=" + email + '}';
    }

    
}

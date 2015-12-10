/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author franc
 */
@XmlRootElement(name = "Administrator") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AdministratorDTO extends UserDTO implements Serializable {

    public AdministratorDTO() {
    }

    public AdministratorDTO(int id, String password, String name, String email) {
        super(id, password, name, email);
    }
    
    
    
}

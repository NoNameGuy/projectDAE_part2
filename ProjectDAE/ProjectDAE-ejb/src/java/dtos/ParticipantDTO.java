/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author franc
 */
public class ParticipantDTO extends UserDTO implements Serializable {
   
    
    public ParticipantDTO() {
    }

    public ParticipantDTO(int id, String password, String name, String email) {
        super(id, password, name, email);
    }

}

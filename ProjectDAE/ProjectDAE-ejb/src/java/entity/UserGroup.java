/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author franc
 */
@Entity(name = "USERS_GROUPS")
public class UserGroup implements Serializable {


    public static enum GROUP {
        Administrator, Responsible, Participant
    }

    @Id
    @Enumerated(EnumType.STRING)
    private GROUP group_Name;

    @Id
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public UserGroup() {
    }

    public UserGroup(GROUP group_Name, User user) {
        this.group_Name = group_Name;
        this.user = user;
    }

    public GROUP getGroup_Name() {
        return group_Name;
    }

    public void setGroup_Name(GROUP group_Name) {
        this.group_Name = group_Name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.group_Name);
        hash = 29 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserGroup other = (UserGroup) obj;
        if (this.group_Name != other.group_Name) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }
}

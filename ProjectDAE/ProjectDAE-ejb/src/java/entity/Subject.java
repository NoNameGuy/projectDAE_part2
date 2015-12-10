/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author franc
 */
@Entity
@Table(name = "SUBJECTS",
        uniqueConstraints
        = @UniqueConstraint(columnNames = {"NAME", "SCHOLARYEAR"}))
@NamedQuery(
        name = "getAllSubjects",
        query = "SELECT s FROM Subject s ORDER BY s.name")

public class Subject implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private int courseYear;
    @NotNull
    private String scholarYear;
    @ManyToMany
    @JoinTable(name = "SUBJECT_PARTICIPANT",
            joinColumns
            = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "PARTICIPANT_ID", referencedColumnName = "ID"))
    private List<Participant> participants;

    public Subject() {
        participants = new LinkedList<>();
    }

    public Subject(int id, String name, int courseYear, String scholarYear) {
        this.id = id;
        this.name = name;
        this.courseYear = courseYear;
        this.scholarYear = scholarYear;
        this.participants = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getScholarYear() {
        return scholarYear;
    }

    public void setScholarYear(String scholarYear) {
        this.scholarYear = scholarYear;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "entity.Subject[ id=" + id + " ]";
    }

}

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
public class SubjectDTO implements Serializable {

    private int id;
    private String name;
    private int courseYear;
    private String scholarYear;

    public SubjectDTO() {

    }

    public SubjectDTO(int id, String name, int courseYear, String scholarYear) {
        this.id = id;
        this.name = name;
        this.courseYear = courseYear;
        this.scholarYear = scholarYear;
    }
    
    public void reset() {
        this.id = 0;
        this.name = null;
        this.courseYear = 0;
        this.scholarYear = null;
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
    
    
}

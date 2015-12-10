/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.SubjectDTO;
import entity.Subject;
import exceptions.EntityAlreadyExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author franc
 */
@Stateless
public class SubjectBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    public void createSubject(int id, String name, int courseYear, String scholarYear)
        throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Subject.class, id) != null) {
                throw new EntityAlreadyExistsException("A subject with that id already exists.");
            }
            Subject subject = new Subject(id, name, courseYear, scholarYear);
            em.persist(subject);
            
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));  
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<SubjectDTO> getAllSubjects() {
        try {
            List<Subject> subjects = (List<Subject>) em.createNamedQuery("getAllSubjects").getResultList();
            return subjectsToDTOs(subjects);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    SubjectDTO subjectToDTO(Subject subject) {
        return new SubjectDTO(
                    subject.getId(),
                    subject.getName(),
                    subject.getCourseYear(),
                    subject.getScholarYear());
    }
    
    List<SubjectDTO> subjectsToDTOs(List<Subject> subjects) {
        List<SubjectDTO> dtos = new ArrayList<>();
        for(Subject s : subjects) {
            dtos.add(subjectToDTO(s));
        }
        return dtos;
    }
}

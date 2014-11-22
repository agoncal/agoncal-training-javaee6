package org.agoncal.training.javaee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
public class Chapter {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String resume;


    // ======================================
    // =            Constructors            =
    // ======================================

    public Chapter() {
    }

    public Chapter(String title, String resume) {
        this.title = title;
        this.resume = resume;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Chapter");
        sb.append("{id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", resume='").append(resume).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
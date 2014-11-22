package org.agoncal.training.javaee;

import javax.persistence.*;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
public class Track {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Float duration;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] wav;
    private String description;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Track() {
    }

    public Track(String title, Float duration, String description) {
        this.title = title;
        this.duration = duration;
        this.description = description;
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

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public byte[] getWav() {
        return wav;
    }

    public void setWav(byte[] wav) {
        this.wav = wav;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Track");
        sb.append("{id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
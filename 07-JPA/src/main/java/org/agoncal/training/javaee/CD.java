package org.agoncal.training.javaee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
@DiscriminatorValue("C")
@NamedQuery(name = "findAllCDs", query = "SELECT c FROM CD c ORDER BY c.id DESC")
public class CD extends Item {

    // ======================================
    // =             Attributes             =
    // ======================================

    private String musicCompany;
    private Integer numberOfCDs;
    private Float totalDuration;
    private String genre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Track> tracks = new ArrayList<Track>();

    // ======================================
    // =            Constructors            =
    // ======================================

    public CD() {
    }

    public CD(String title, Float price, String description, String musicCompany, Integer numberOfCDs, Float totalDuration, String genre) {
        super(title, price, description);
        this.musicCompany = musicCompany;
        this.numberOfCDs = numberOfCDs;
        this.totalDuration = totalDuration;
        this.genre = genre;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getMusicCompany() {
        return musicCompany;
    }

    public void setMusicCompany(String musicCompany) {
        this.musicCompany = musicCompany;
    }

    public Integer getNumberOfCDs() {
        return numberOfCDs;
    }

    public void setNumberOfCDs(Integer numberOfCDs) {
        this.numberOfCDs = numberOfCDs;
    }

    public Float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CD");
        sb.append("{id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", musicCompany='").append(musicCompany).append('\'');
        sb.append(", numberOfCDs=").append(numberOfCDs);
        sb.append(", totalDuration=").append(totalDuration);
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", tracks=").append(tracks);
        sb.append('}');
        return sb.toString();
    }
}
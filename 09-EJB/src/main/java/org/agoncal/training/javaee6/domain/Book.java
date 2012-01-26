package org.agoncal.training.javaee6.domain;

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
@DiscriminatorValue("B")
@NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b ORDER BY b.id DESC")
@EntityListeners(DebugListener.class)
public class Book extends Item {

    // ======================================
    // =             Attributes             =
    // ======================================

    private String isbn;
    private Integer nbOfPage;
    private Boolean illustrations;

    // annotation can be omitted thanks to programming by exception
    @Enumerated
    private Language contentLanguage;

    // annotations can be omitted thanks to programming by exception
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tags")
    private List<String> tags = new ArrayList<String>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Chapter> chapters = new ArrayList<Chapter>();

    // ======================================
    // =            Constructors            =
    // ======================================

    public Book() {
    }

    public Book(String title, Float price, String description, String isbn, Integer nbOfPage, Boolean illustrations, Language contentLanguage) {
        super(title, price, description);
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
        this.contentLanguage = contentLanguage;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    public Language getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(Language contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Book");
        sb.append("{id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", nbOfPage=").append(nbOfPage);
        sb.append(", illustrations=").append(illustrations);
        sb.append(", contentLanguage=").append(contentLanguage);
        sb.append(", tags=").append(tags);
        sb.append(", chapters=").append(chapters);
        sb.append('}');
        return sb.toString();
    }
}
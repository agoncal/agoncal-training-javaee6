package org.agoncal.training.javaee.web;

import org.agoncal.training.javaee.domain.Book;
import org.agoncal.training.javaee.domain.CD;
import org.agoncal.training.javaee.domain.Language;
import org.agoncal.training.javaee.service.ItemEJB;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Named
@RequestScoped
public class ItemController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private ItemEJB itemEJB;

    private Book book = new Book();
    private List<Book> bookList = new ArrayList<Book>();
    private String tags;

    private CD cd = new CD();
    private List<CD> cdList = new ArrayList<CD>();

    private Logger logger = Logger.getLogger("org.agoncal.training.javaee6");

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void initList() {
        logger.fine("ItemController.initList()");
        bookList = itemEJB.findAllBooks();
        cdList = itemEJB.findAllCDs();
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doCreateBook() {
        logger.fine("ItemController.doCreateBook():" + book);
        book.setTags(transformToList(tags));
        itemEJB.createBook(book);
        bookList = itemEJB.findAllBooks();
        book = new Book();
        return "newBook.xhtml";
    }

    public String doCreateCD() {
        logger.fine("ItemController.doCreateCD():" + cd);
        itemEJB.createCD(cd);
        cdList = itemEJB.findAllCDs();
        cd = new CD();
        return "newCD.xhtml";
    }

    // ======================================
    // =           Private methods          =
    // ======================================

    private List<String> transformToList(String tagsRequestParameter) {
        logger.finer("ItemController.transformToList():" + tagsRequestParameter);
        if (tagsRequestParameter == null)
            return null;
        List<String> listOfTags = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(tagsRequestParameter, ",");
        while (tokens.hasMoreElements()) {
            listOfTags.add(((String) tokens.nextElement()).trim());
        }
        return listOfTags;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public List<CD> getCdList() {
        return cdList;
    }

    public void setCdList(List<CD> cdList) {
        this.cdList = cdList;
    }

    public Language[] getLanguages() {
        return Language.values();
    }
}
package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.domain.Book;
import org.agoncal.training.javaee.domain.CD;
import org.agoncal.training.javaee.domain.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Stateless
public class ItemEJB {

    // ======================================
    // =             Attributes             =
    // ======================================

    @PersistenceContext(unitName = "trainingPU")
    private EntityManager em;

    // ======================================
    // =          Business methods          =
    // ======================================

    public List<Item> findAllItems() {
        return em.createNamedQuery("findAllItems", Item.class).getResultList();
    }

    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }

    public Book findBook(Long id) {
        return em.find(Book.class, id);
    }

    public void removeBook(Book book) {
        em.remove(em.merge(book));
    }

    public List<Book> findAllBooks() {
        return em.createNamedQuery("findAllBooks", Book.class).getResultList();
    }

    public CD createCD(CD cd) {
        em.persist(cd);
        return cd;
    }

    public CD findCD(Long id) {
        return em.find(CD.class, id);
    }

    public void removeCD(CD cd) {
        em.remove(em.merge(cd));
    }

    public List<CD> findAllCDs() {
        return em.createNamedQuery("findAllCDs", CD.class).getResultList();
    }
}

package org.agoncal.training.javaee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class BookTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static String PERSISTENCE_UNIT_NAME = "trainingPU";

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void close() {
        em.close();
        emf.close();
    }

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateABook() {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
        Long id = book.getId();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(book.getTitle(), "H2G2");

        // Updates the book
        tx.begin();
        book.setTitle("Hitchhiker's Guide");
        tx.commit();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(book.getTitle(), "Hitchhiker's Guide");

        // Deletes the book
        tx.begin();
        em.remove(book);
        tx.commit();

        // Checks the book has been deleted
        assertNull("Book should has been deleted", em.find(Book.class, id));
    }

    @Test
    public void shouldCreateABookWithTags() {

        // Creates a book with tags
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);
        List<String> tags = new ArrayList<String>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
        Long id = book.getId();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(book.getTitle(), "H2G2");

        // Checks the number of tags
        assertEquals(book.getTags().size(), 2);

        // Deletes the book
        tx.begin();
        em.remove(book);
        tx.commit();

        // Checks the book has been deleted
        assertNull("Book should has been deleted", em.find(Book.class, id));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateABookWithANullTitle() {

        // Creates a book with null title
        Book book = new Book(null, 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
    }

    @Test
    public void shouldUpdateTheBookLanguage() {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
        Long id = book.getId();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(book.getContentLanguage(), Language.ENGLISH);

        // Updates the book
        tx.begin();
        book.setContentLanguage(Language.FRENCH);
        tx.commit();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(book.getContentLanguage(), Language.FRENCH);

        // Deletes the book
        tx.begin();
        em.remove(book);
        tx.commit();

        assertNull("Book should has been deleted", em.find(Book.class, id));
    }
}
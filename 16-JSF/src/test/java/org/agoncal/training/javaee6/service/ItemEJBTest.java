package org.agoncal.training.javaee6.service;

import org.agoncal.training.javaee6.domain.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ItemEJBTest {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static EJBContainer ec;
    private static Context ctx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File[]{new File("target/classes"), new File("target/test-classes")});
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();
    }

    @AfterClass
    public static void closeContainer() {
        if (ec != null) {
            ec.close();
        }
    }

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldCreateABook() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists a book
        book = itemEJB.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Finds the book by primary key
        book = itemEJB.findBook(book.getId());
        assertEquals(book.getTitle(), "H2G2");

        // Checks the ISBN number is 13 digits
        assertTrue("ISBN should be 13 digits", book.getIsbn().startsWith("13-84356-"));

        // Deletes the book
        itemEJB.removeBook(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", itemEJB.findBook(book.getId()));
    }

    @Test
    public void shouldFindAllBooks() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Finds all books
        int initialNumberOfBooks = itemEJB.findAllBooks().size();

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        book = itemEJB.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Checks the ISBN number is 13 digits
        assertTrue("ISBN should be 13 digits", book.getIsbn().startsWith("13-84356-"));

        // Finds all books
        assertEquals("Should have one extra book", initialNumberOfBooks + 1, itemEJB.findAllBooks().size());

        // Deletes the book
        itemEJB.removeBook(book);

        // Finds all books
        assertEquals("Should have initial number of books", initialNumberOfBooks, itemEJB.findAllBooks().size());
    }

    @Test
    public void shouldCreateABookWithTags() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Creates a book with tags
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);
        List<String> tags = new ArrayList<String>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Persists the book
        book = itemEJB.createBook(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = itemEJB.findBook(id);
        assertEquals(book.getTitle(), "H2G2");

        // Checks the ISBN number is 13 digits
        assertTrue("ISBN should be 13 digits", book.getIsbn().startsWith("13-84356-"));

        // Checks the number of tags
        assertEquals(book.getTags().size(), 2);

        // Deletes the book
        itemEJB.removeBook(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", itemEJB.findBook(id));
    }

    @Test
    public void shouldCreateABookWithChapters() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Creates a book with tags
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);
        List<String> tags = new ArrayList<String>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Adds chapters to the book
        Chapter chapter1 = new Chapter("Arriving on earth", "blah blah blah blah blah");
        Chapter chapter2 = new Chapter("Restaurant of the universe", "Forty two");
        List<Chapter> chapters = new ArrayList<Chapter>();
        chapters.add(chapter1);
        chapters.add(chapter2);
        book.setChapters(chapters);

        // Persists the book
        book = itemEJB.createBook(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = itemEJB.findBook(id);
        assertEquals(book.getTitle(), "H2G2");

        // Checks the ISBN number is 13 digits
        assertTrue("ISBN should be 13 digits", book.getIsbn().startsWith("13-84356-"));

        // Checks the number of tags
        assertEquals(book.getTags().size(), 2);

        // Checks the number of chapters
        assertEquals(book.getChapters().size(), 2);

        // Deletes the book
        itemEJB.removeBook(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", itemEJB.findBook(id));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateABookWithANullTitle() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Creates a book with null title
        Book book = new Book(null, 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        itemEJB.createBook(book);
    }

    @Test
    public void shouldCreateACD() throws Exception {
        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Counts all the cds in the database
        int nbCDs = itemEJB.findAllCDs().size();

        // Creates a cd
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the cd to the database
        cd = itemEJB.createCD(cd);
        assertNotNull("ID should not be null", cd.getId());

        // Finds the cd by primary key
        cd = itemEJB.findCD(cd.getId());
        assertEquals(cd.getTitle(), "St Pepper");

        // Checks that there is an extra cd in the database
        assertEquals("Should have an extra cd", itemEJB.findAllCDs().size(), nbCDs + 1);

        // Removes the cd
        itemEJB.removeCD(cd);

        // Checks that the extra cd has been removed
        assertEquals("Should have got rid of the extra cd", itemEJB.findAllCDs().size(), nbCDs);
    }

    @Test
    public void shouldFindAllCDs() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Finds all books
        int initialNumberOfCDs = itemEJB.findAllCDs().size();

        // Creates a cd
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the cd
        cd = itemEJB.createCD(cd);
        assertNotNull("ID should not be null", cd.getId());

        // Finds all cds
        assertEquals("Should have one extra cd", initialNumberOfCDs + 1, itemEJB.findAllCDs().size());

        // Deletes the cd
        itemEJB.removeCD(cd);

        // Finds all cds
        assertEquals("Should have initial number of cds", initialNumberOfCDs, itemEJB.findAllCDs().size());
    }

    @Test
    public void shouldCreateACDWithTracks() throws Exception {
        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Creates a CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Tracks
        Track track1 = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");
        Track track2 = new Track("Fixing a Hole", 3.34f, "Beleive it or not, this song is about drugs");
        List<Track> tracks = new ArrayList<Track>();
        tracks.add(track1);
        tracks.add(track2);
        cd.setTracks(tracks);

        // Persists the cd to the database
        cd = itemEJB.createCD(cd);
        assertNotNull("ID should not be null", cd.getId());
        Long id = cd.getId();

        // Finds the CD by primary key
        cd = itemEJB.findCD(id);
        assertEquals(cd.getTitle(), "St Pepper");

        // Checks the number of tracks
        assertEquals(cd.getTracks().size(), 2);

        // Removes the cd
        itemEJB.removeCD(cd);

        // Checks that the extra cd has been removed
        assertNull("CD should has been deleted", itemEJB.findCD(id));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateACDWithANullTitle() throws Exception {
        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Creates a CD
        CD cd = new CD(null, 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the cd to the database
        itemEJB.createCD(cd);
    }
}

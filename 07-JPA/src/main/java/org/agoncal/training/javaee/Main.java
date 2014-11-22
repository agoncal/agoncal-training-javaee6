package org.agoncal.training.javaee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

    private static Logger logger = Logger.getLogger("org.agoncal.training.javaee6");

    public static void main(String[] args) {
        // Creates an instance of book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);
        // Tags
        List<String> tags = new ArrayList<String>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);
        // Chapters
        Chapter chapter1 = new Chapter("Arriving on earth", "blah blah blah blah blah");
        Chapter chapter2 = new Chapter("Restaurant of the universe", "Forty two");
        List<Chapter> chapters = new ArrayList<Chapter>();
        chapters.add(chapter1);
        chapters.add(chapter2);
        book.setChapters(chapters);

        // Creates an instance of CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");
        // Tracks
        Track track1 = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");
        Track track2 = new Track("Fixing a Hole", 3.34f, "Beleive it or not, this song is about drugs");
        List<Track> tracks = new ArrayList<Track>();
        tracks.add(track1);
        tracks.add(track2);
        cd.setTracks(tracks);

        // Gets an entity manager and a transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trainingPU");
        EntityManager em = emf.createEntityManager();

        // Persists the book to the database
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(book);
        tx.commit();

        // Persists the CD to the database
        tx.begin();
        em.persist(cd);
        tx.commit();

        // Finds all the items
        logger.info("##### All items");
        TypedQuery<Item> query = em.createNamedQuery("findAllItems", Item.class);
        List<Item> items = query.getResultList();
        for (Item oneItem : items) {
            logger.info("# " + oneItem);
        }

        // Finds all the CDs
        logger.info("##### All CDs");
        List<CD> cds = em.createNamedQuery("findAllCDs", CD.class).getResultList();
        for (CD oneCD : cds) {
            logger.info("# " + oneCD);
        }

        // Finds all the Books
        logger.info("##### All Books");
        List<Book> books = em.createNamedQuery("findAllBooks", Book.class).getResultList();
        for (Book oneBook : books) {
            logger.info("# " + oneBook);
        }

        em.close();
        emf.close();

    }
}



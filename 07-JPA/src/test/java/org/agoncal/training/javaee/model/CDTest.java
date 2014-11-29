package org.agoncal.training.javaee.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class CDTest extends AbstractPersistentTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateACD() {

        // Creates a CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the CD
        tx.begin();
        em.persist(cd);
        tx.commit();
        Long id = cd.getId();

        // Finds the CD by primary key
        cd = em.find(CD.class, id);
        assertEquals(cd.getTitle(), "St Pepper");

        // Updates the CD
        tx.begin();
        cd.setTitle("Help");
        tx.commit();

        // Finds the CD by primary key
        cd = em.find(CD.class, id);
        assertEquals(cd.getTitle(), "Help");

        // Deletes the CD
        tx.begin();
        em.remove(cd);
        tx.commit();

        // Checks the CD has been deleted
        assertNull("CD should has been deleted", em.find(CD.class, id));
    }

    @Test
    public void shouldFindAllCDs() {

        // Finds all CDs
        assertEquals("Should have no CD", 0, em.createNamedQuery("findAllCDs", CD.class).getResultList().size());

        // Creates a CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the CD
        tx.begin();
        em.persist(cd);
        tx.commit();

        // Finds all CDs
        assertEquals("Should have one CD", 1, em.createNamedQuery("findAllCDs", CD.class).getResultList().size());

        // Deletes the CD
        tx.begin();
        em.remove(cd);
        tx.commit();

        // Finds all CDs
        assertEquals("Should have no CD", 0, em.createNamedQuery("findAllCDs", CD.class).getResultList().size());
    }

    @Test
    public void shouldCreateACDWithTracks() {

        // Creates a CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Adds tracks
        Track track1 = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");
        Track track2 = new Track("Fixing a Hole", 3.34f, "Beleive it or not, this song is about drugs");
        List<Track> tracks = new ArrayList<Track>();
        tracks.add(track1);
        tracks.add(track2);
        cd.setTracks(tracks);

        // Persists the CD
        tx.begin();
        em.persist(cd);
        tx.commit();
        Long id = cd.getId();

        // Finds the CD by primary key
        cd = em.find(CD.class, id);
        assertEquals(cd.getTitle(), "St Pepper");

        // Finds the track by primary key
        track1 = em.find(Track.class, track1.getId());
        assertEquals(track1.getTitle(), "Sgt Pepper Lonely Heart Club Ban");

        // Checks the number of tracks
        assertEquals(cd.getTracks().size(), 2);

        // Deletes the CD
        tx.begin();
        em.remove(cd);
        tx.commit();

        // Checks the CD has been deleted
        assertNull("CD should has been deleted", em.find(CD.class, id));
        assertNull("Track should has been deleted", em.find(Track.class, track1.getId()));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateACDWithANullTitle() {

        // Creates a CD with null title
        CD cd = new CD(null, 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the CD
        tx.begin();
        em.persist(cd);
        tx.commit();
    }
}
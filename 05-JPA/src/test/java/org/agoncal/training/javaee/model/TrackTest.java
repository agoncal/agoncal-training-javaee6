package org.agoncal.training.javaee.model;

import org.agoncal.training.javaee.model.Chapter;
import org.agoncal.training.javaee.model.Track;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class TrackTest extends AbstractPersistentTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateATrack() {
        Track track = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");

        // Creates a track
        tx.begin();
        em.persist(track);
        tx.commit();
        Long id = track.getId();

        // Finds the track by primary key
        track = em.find(Track.class, id);
        assertEquals(track.getTitle(), "Sgt Pepper Lonely Heart Club Ban");

        // Updates the track
        tx.begin();
        track.setTitle("Sgt Pepper Lonely Heart Club Band");
        tx.commit();

        // Finds the chapter by primary key
        track = em.find(Track.class, id);
        assertEquals(track.getTitle(), "Sgt Pepper Lonely Heart Club Band");

        // Deletes the chapter
        tx.begin();
        em.remove(track);
        tx.commit();

        assertNull("Track should has been deleted", em.find(Chapter.class, id));
    }
}
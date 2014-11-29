package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.Chapter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class ChapterTest extends AbstractPersistentTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAnChapter() {
        Chapter chapter = new Chapter("Gone with the wind", "Scarlet jumps into the bed, crying.");

        // Creates a book
        tx.begin();
        em.persist(chapter);
        tx.commit();
        Long id = chapter.getId();

        // Finds the chapter by primary key
        chapter = em.find(Chapter.class, id);
        assertEquals(chapter.getTitle(), "Gone with the wind");

        // Updates the chapter
        tx.begin();
        chapter.setTitle("Just gone");
        tx.commit();

        // Finds the chapter by primary key
        chapter = em.find(Chapter.class, id);
        assertEquals(chapter.getTitle(), "Just gone");

        // Deletes the chapter
        tx.begin();
        em.remove(chapter);
        tx.commit();

        assertNull("Chapter should has been deleted", em.find(Chapter.class, id));
    }
}
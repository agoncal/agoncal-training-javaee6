package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class ItemTest extends AbstractPersistentTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAnItem() {

        // Creates an item
        Item item = new Item("Pencil", 2.35f, "A red pencil");

        // Persists the item
        tx.begin();
        em.persist(item);
        tx.commit();
        Long id = item.getId();

        // Finds the item by primary key
        item = em.find(Item.class, id);
        assertEquals(item.getTitle(), "Pencil");

        // Updates the item
        tx.begin();
        item.setTitle("Red pen");
        tx.commit();

        // Finds the item by primary key
        item = em.find(Item.class, id);
        assertEquals(item.getTitle(), "Red pen");

        // Deletes the item
        tx.begin();
        em.remove(item);
        tx.commit();

        // Checks the item has been deleted
        assertNull("Item should has been deleted", em.find(Item.class, id));
    }

    @Test
    public void shouldFindAllItems() {

        // Finds all items
        assertEquals("Should have no item", 0, em.createNamedQuery("findAllItems", Item.class).getResultList().size());

        // Creates an item
        Item item = new Item("Pencil", 2.35f, "A red pencil");

        // Persists an item
        tx.begin();
        em.persist(item);
        tx.commit();

        // Finds all items
        assertEquals("Should have one item", 1, em.createNamedQuery("findAllItems", Item.class).getResultList().size());

        // Deletes the item
        tx.begin();
        em.remove(item);
        tx.commit();

        // Finds all items
        assertEquals("Should have no item", 0, em.createNamedQuery("findAllItems", Item.class).getResultList().size());
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateAnItemWithANullTitle() {

        // Creates an item with null title
        Item item = new Item(null, 2.35f, "A red pencil");

        // Persists the item
        tx.begin();
        em.persist(item);
        tx.commit();
    }
}
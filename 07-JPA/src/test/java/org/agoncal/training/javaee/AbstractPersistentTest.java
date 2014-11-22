package org.agoncal.training.javaee;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public abstract class AbstractPersistentTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static String PERSISTENCE_UNIT_NAME = "trainingPU";

    private static EntityManagerFactory emf;
    protected static EntityManager em;
    protected static EntityTransaction tx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initEntityManager() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void closeEntityManager() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }

    @Before
    public void initTransaction() {
        tx = em.getTransaction();
    }
}
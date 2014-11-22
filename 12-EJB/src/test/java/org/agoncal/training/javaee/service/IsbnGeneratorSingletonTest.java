package org.agoncal.training.javaee.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class IsbnGeneratorSingletonTest {

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
        properties.put(EJBContainer.MODULES, new File("target/classes"));
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
    public void shouldGenerateAIsbnNumber() throws Exception {
        // Looks up for the EJB
        IsbnGeneratorSingleton isbnGenerator = (IsbnGeneratorSingleton) ctx.lookup("java:global/classes/IsbnGeneratorSingleton");

        // Checks the ISBN is not null
        assertNotNull("ISBN should not be null", isbnGenerator.generateNumber());
    }
}

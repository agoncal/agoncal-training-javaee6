package org.agoncal.training.javaee6.service;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class IsbnGeneratorTest {

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldGenerateAIsbnNumber() throws Exception {
        // Checks the ISBN is not null
        assertNotNull("ISBN should not be null", new IsbnGenerator().generateNumber());
    }
}

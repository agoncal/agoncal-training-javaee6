package org.agoncal.training.javaee6.service;

import javax.annotation.PostConstruct;
import javax.interceptor.Interceptors;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@EightDigits
public class IssnGenerator implements NumberGenerator {

    // ======================================
    // =             Attributes             =
    // ======================================

    private int number;

    private Logger logger = Logger.getLogger("org.agoncal.training.javaee6");

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void init() {
        number = Math.abs(new Random().nextInt());
        logger.fine("IssnGenerator.init():" + number);
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    @Interceptors(LoggingInterceptor.class)
    public String generateNumber() {
        String isbn = "8-" + number++;
        logger.fine("IssnGenerator.generateNumber():" + isbn);
        return isbn;
    }

}

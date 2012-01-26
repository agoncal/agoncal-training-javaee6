package org.agoncal.training.javaee6;

import javax.persistence.*;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class DebugListener {

    // ======================================
    // =             Attributes             =
    // ======================================

    private Logger logger = Logger.getLogger("org.agoncal.training.javaee6");

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @PrePersist
    void prePersist(Object object) {
        logger.finest("### DebugListener.prePersist(" + object + ")");
    }

    @PostPersist
    void postPersist(Object object) {
        logger.finest("### DebugListener.postPersist(" + object + ")");
    }

    @PreUpdate
    void preUpdate(Object object) {
        logger.finest("### DebugListener.preUpdate(" + object + ")");
    }

    @PostUpdate
    void postUpdate(Object object) {
        logger.finest("### DebugListener.postUpdate(" + object + ")");
    }

    @PreRemove
    void preRemove(Object object) {
        logger.finest("### DebugListener.preRemove(" + object + ")");
    }

    @PostRemove
    void postRemove(Object object) {
        logger.finest("### DebugListener.postRemove(" + object + ")");
    }

    @PostLoad
    void postLoad(Object object) {
        logger.finest("### DebugListener.postLoad(" + object + ")");
    }
}

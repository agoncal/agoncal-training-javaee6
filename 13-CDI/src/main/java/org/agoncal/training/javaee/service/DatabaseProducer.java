package org.agoncal.training.javaee.service;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseProducer {

    @Produces
    @PersistenceContext(unitName = "trainingPU")
    private EntityManager em;
}

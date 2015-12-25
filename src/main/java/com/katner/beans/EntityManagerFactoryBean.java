package com.katner.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by michal on 23.12.15.
 */
@ManagedBean
@ApplicationScoped
public class EntityManagerFactoryBean {
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}

package com.katner.beans;

import com.katner.model.AdminMessage;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by michal on 28.12.15.
 */
@ManagedBean
@ApplicationScoped
public class AdminMessagesBean {
    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<AdminMessage> getMessageList() {
        return em.createQuery("from AdminMessage ").getResultList();
    }





}

package com.katner.beans;

import com.katner.model.Book;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by michal on 23.12.15.
 */
@ManagedBean
@SessionScoped
public class BookListBean {
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;

    public List<Book> getAllBooks()
    {
        return em.createQuery("from Book ").getResultList();
    }


}

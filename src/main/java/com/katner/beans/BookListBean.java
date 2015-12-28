package com.katner.beans;

import com.katner.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    private Integer authorId = -1;
    private Integer tagId = -1;

    private List<Book> books;

    public void setBooks(List<Book> books) {
        this.books = books;
    }

        @PostConstruct
    public void init(){
         books = em.createQuery("from Book ").getResultList();
            authorId = -1;
            tagId = -1;

    }

    public String getAllBooks() {
         books = em.createQuery("from Book ").getResultList();
        return "listBooks";
    }


    public List<Book> getBooks() {
        return books;
    }

    public String booksByAuthor() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String aid = (String) map.get("authorId");
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Book.class, "book").createAlias("authors", "a").createAlias("tags", "t");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (aid != null) {
            criteria.add(Restrictions.eq("a.id", Integer.parseInt(aid)));
        }
        books = criteria.list();
        return "listBooks";
    }

    public String booksByTag() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String tid = (String) map.get("tagId");
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Book.class, "book").createAlias("authors", "a").createAlias("tags", "t");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (tid != null) {
            criteria.add(Restrictions.eq("t.id", Integer.parseInt(tid)));
        }
        books = criteria.list();
        return "listBooks";
    }


}

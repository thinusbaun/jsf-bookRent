package com.katner.beans;

import com.katner.model.Author;
import com.katner.model.Book;
import com.katner.model.Tag;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 10.01.16.
 */
@ManagedBean
@SessionScoped
public class BookAdminBean {
    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;

    @ManagedProperty("#{bookListBean}")
    private BookListBean bookListBean;
    private List<Book> allBooks;
    private List<SelectItem> allAuthors;
    private List<SelectItem> allTags;

    public BookListBean getBookListBean() {
        return bookListBean;
    }

    public void setBookListBean(BookListBean bookListBean) {
        this.bookListBean = bookListBean;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<SelectItem> getAllTags() {
        return allTags;
    }

    public void setAllTags(List<SelectItem> allTags) {
        this.allTags = allTags;
    }

    public List<SelectItem> getAllAuthors() {
        return allAuthors;
    }

    public void setAllAuthors(List<SelectItem> allAuthors) {
        this.allAuthors = allAuthors;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }


    @PostConstruct
    public void init() {
        allBooks = em.createQuery("from Book ").getResultList();
        List<Author> authors = em.createQuery("from Author ").getResultList();
        List<Tag> tags = em.createQuery("from Tag ").getResultList();
        List<SelectItem> tmp = new ArrayList<SelectItem>();
        for (Author a : authors) {
            tmp.add(new SelectItem(a.getId(), a.getName()));
        }
        allAuthors = tmp;
        tmp = new ArrayList<SelectItem>();
        for (Tag t : tags) {
            tmp.add(new SelectItem(t.getId(), t.getTitle()));
        }
        allTags = tmp;

    }

    public void updateBook() {
        Integer bookid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookid"));
        em.getTransaction().begin();
//        Book bookToMerge = em.find(Book.class, bookid);
        for (Book b : allBooks) {
            if (b.sAuthors != null) {
                Integer asd = 123;
                return;
            }
        }
        for (Book b : allBooks) {
            if (b.getId() == bookid) {
                List<Author> authors = new ArrayList<Author>();
                for (int i = 0; i < b.sAuthors.size(); i++) {
                    authors.add(em.find(Author.class, Integer.parseInt(b.sAuthors.get(i))));
                }
                b.setAuthors(authors);
                em.merge(b);
                bookListBean.searchBook(bookid).setAuthors(authors);
            }
        }
        em.getTransaction().commit();
    }

}

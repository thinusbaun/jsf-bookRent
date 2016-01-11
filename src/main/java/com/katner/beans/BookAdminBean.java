package com.katner.beans;

import com.katner.model.Author;
import com.katner.model.Book;
import com.katner.model.Tag;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void updateBook(AjaxBehaviorEvent event) {
        final Integer bookid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookid"));
        Optional<Book> book = allBooks.stream().filter(a -> a.getId() == bookid).findFirst();
        try {
            if (!book.isPresent()) {
                return;
            }
            em.getTransaction().begin();
            List<Author> authors = new ArrayList<Author>();
            for (String authoridString : book.get().sAuthors) {
                authors.add(em.find(Author.class, Integer.parseInt(authoridString)));
            }
            book.get().setAuthors(authors);
            em.merge(book.get());
            bookListBean.searchBook(bookid).setAuthors(authors);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}

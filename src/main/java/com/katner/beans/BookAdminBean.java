package com.katner.beans;

import com.katner.model.Author;
import com.katner.model.Book;
import com.katner.model.BookCopy;
import com.katner.model.Tag;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.*;

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
    private String newBookTitle;
    private String newBookIsbn;
    private List<BookCopy> copies;
    private Integer bookIdForCopy;

    public List<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

    public String getNewBookTitle() {
        return newBookTitle;
    }

    public void setNewBookTitle(String newBookTitle) {
        this.newBookTitle = newBookTitle;
    }

    public String getNewBookIsbn() {
        return newBookIsbn;
    }

    public void setNewBookIsbn(String newBookIsbn) {
        this.newBookIsbn = newBookIsbn;
    }

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

    public void updateAuthorsInBook(AjaxBehaviorEvent event) {
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

    public void updateTagsInBook(AjaxBehaviorEvent event) {
        final Integer bookid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookid"));
        Optional<Book> book = allBooks.stream().filter(a -> a.getId() == bookid).findFirst();
        try {
            if (!book.isPresent()) {
                return;
            }
            em.getTransaction().begin();
            List<Tag> tags = new ArrayList<Tag>();
            for (String tagidString : book.get().sTags) {
                tags.add(em.find(Tag.class, Integer.parseInt(tagidString)));
            }
            book.get().setTags(tags);
            em.merge(book.get());
            bookListBean.searchBook(bookid).setTags(tags);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void removeBook() {
        final Integer bookid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookid"));
        Optional<Book> book = allBooks.stream().filter(a -> a.getId() == bookid).findFirst();
        try {
            em.getTransaction().begin();
            em.remove(book.get());
            em.getTransaction().commit();
            allBooks.removeIf(a -> a.getId() == bookid);
            bookListBean.removeBook(bookid);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void addBook() {
        try {
            em.getTransaction().begin();
            Book b = new Book();
            b.setTitle(newBookTitle);
            b.setIsbn(newBookIsbn);
            b.setAddedDate(new Date(Calendar.getInstance().getTime().getTime()));
            em.persist(b);
            em.getTransaction().commit();
            allBooks.add(b);
            newBookIsbn = "";
            newBookTitle = "";
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public String showAddCopy() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt((String) map.get("bookid"));
        copies = em.createQuery("from BookCopy c where c.book.id = :id").setParameter("id", id).getResultList();
        bookIdForCopy = id;
        return "copiesAdmin";
    }

    public String removeCopy() {
        final Integer bookid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookid"));
        final Integer copyid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("copyid"));
        Optional<Book> book = allBooks.stream().filter(a -> a.getId() == bookid).findFirst();
        try {
            em.getTransaction().begin();
            List<BookCopy> bookCopyList = book.get().getCopies();
            Optional<BookCopy> copy = bookCopyList.stream().filter(a -> a.getId() == copyid).findFirst();
            em.remove(copy.get());
            bookCopyList.removeIf(a -> a.getId() == copyid);
            copies.removeIf(a -> a.getId() == copyid);
            book.get().setCopies(bookCopyList);
            em.merge(book.get());
            em.getTransaction().commit();
            Book blbBook = bookListBean.searchBook(bookid);
            List<BookCopy> blbCopyList = blbBook.getCopies();
            blbCopyList.removeIf(a -> a.getId() == copyid);
            blbBook.setCopies(blbCopyList);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return "";
    }

    public String addCopy() {
        Integer bookid = bookIdForCopy;
        Optional<Book> book = allBooks.stream().filter(a -> a.getId() == bookid).findAny();
        try {
            em.getTransaction().begin();
            BookCopy b = new BookCopy();
            b.setBook(book.get());
            em.persist(b);
            book.get().getCopies().add(b);
            em.merge(book.get());
            em.getTransaction().commit();
            copies.add(b);
            bookListBean.searchBook(bookid).getCopies().add(b);
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return "";
    }

}

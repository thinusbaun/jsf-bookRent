package com.katner.beans;

import com.katner.model.BookCopy;
import com.katner.model.Rental;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by michal on 16.01.16.
 */
@ManagedBean
@SessionScoped
public class RentalAdminBean {
    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;

    @ManagedProperty("#{bookListBean}")
    private BookListBean bookListBean;

    private List<Rental> allRentals;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public BookListBean getBookListBean() {
        return bookListBean;
    }

    public void setBookListBean(BookListBean bookListBean) {
        this.bookListBean = bookListBean;
    }

    public List<Rental> getAllRentals() {
        return allRentals;
    }

    public void setAllRentals(List<Rental> allRentals) {
        this.allRentals = allRentals;
    }

    @PostConstruct
    public void init() {
        allRentals = em.createQuery("from Rental").getResultList();
    }

    public String viewRentals() {
        em.getTransaction().begin();
        allRentals = em.createQuery("from Rental").getResultList();
        em.getTransaction().commit();
        return "admin/rentalsAdmin";
    }

    public String returnCopy() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        Integer bookid = Integer.parseInt((String) map.get("bookid"));
        Integer copyid = Integer.parseInt((String) map.get("copyid"));
        Integer rentalid = Integer.parseInt((String) map.get("rentalid"));
        Session session = em.unwrap(Session.class);
        session.getTransaction().begin();
        Rental rental = session.get(Rental.class, rentalid);
        rental.setReturnDate(new Date(Calendar.getInstance().getTime().getTime()));
        session.saveOrUpdate(rental);
        session.getTransaction().commit();
        for (BookCopy c : bookListBean.searchBook(bookid).getCopies()) {
            for (Rental r : c.getRentals()) {
                if (r.getId() == rentalid) {
                    r.setReturnDate(rental.getReturnDate());
                }
            }
        }
        return "";
    }

    public String removeRental() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        Integer bookid = Integer.parseInt((String) map.get("bookid"));
        Integer copyid = Integer.parseInt((String) map.get("copyid"));
        Integer rentalid = Integer.parseInt((String) map.get("rentalid"));
        Session session = em.unwrap(Session.class);
        session.getTransaction().begin();
        Rental rental = session.get(Rental.class, rentalid);
        session.delete(rental);
        session.getTransaction().commit();
        bookListBean.searchBook(bookid).getCopies().forEach((c) -> {
            c.getRentals().removeIf(a -> a.getId() == rentalid);
        });
        allRentals.removeIf(a -> a.getId() == rentalid);
        return "";
    }
}

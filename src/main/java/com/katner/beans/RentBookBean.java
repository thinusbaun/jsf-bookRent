package com.katner.beans;

import com.katner.model.BookCopy;
import com.katner.model.Rental;
import org.hibernate.Session;

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
 * Created by michal on 30.12.15.
 */
@ManagedBean
@SessionScoped
public class RentBookBean {
    private List<BookCopy> copies;
    private Integer bookid;

    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String showCopies() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt((String) map.get("bookid"));
        copies = em.createQuery("from BookCopy c where c.book.id = :id").setParameter("id", id).getResultList();
        return "showCopies";
    }

    public String rentCopy() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt((String) map.get("copyid"));
        Session session = em.unwrap(Session.class);
        BookCopy copy = session.get(BookCopy.class, id);
        Rental rental = new Rental();
        rental.setRentalDate(new Date(Calendar.getInstance().getTime().getTime()));
        rental.setBookCopy(copy);
        rental.setUser(userBean.getUser());
        session.getTransaction().begin();
        session.persist(rental);
        session.getTransaction().commit();
        session.refresh(searchCopy(id));
        return showCopies();
    }

    BookCopy searchCopy(Integer id) {
        for (BookCopy copy : copies) {
            if (copy.getId() == id) {
                return copy;
            }
        }
        return null;
    }
}

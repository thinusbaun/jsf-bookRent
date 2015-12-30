package com.katner.beans;

import com.katner.Hasher;
import com.katner.model.AuthUser;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by michal on 30.12.15.
 */
@ManagedBean
@RequestScoped
public class RegisterBean {
    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;
    private String firstname, lastname, username, password, email;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String register() {
        try {
            AuthUser user = new AuthUser();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(Hasher.encode(password));
            user.setDateJoined(new Date(Calendar.getInstance().getTime().getTime()));

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            return "register";
        }
        return "login";

    }
}

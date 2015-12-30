package com.katner.beans;

import com.katner.Hasher;
import com.katner.model.AuthUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

/**
 * Created by michal on 29.12.15.
 */
@ManagedBean
@SessionScoped
public class UserBean {
    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;
    private String username = null;
    private String password = null;
    private AuthUser user = null;

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
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

    public String login() {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(AuthUser.class, "user");
        criteria.add(Restrictions.eq("username", username));
        criteria.setMaxResults(1);
        user = (AuthUser) criteria.uniqueResult();
        if (user != null) {
            if (Hasher.checkPassword(password, user.getPassword()))
                return "listBooks";
            else {
                user = null;
                return "login";
            }
        } else {
            return "login";
        }
    }

    public String logout() {
        user = null;
        username = null;
        password = null;
        return "index";
    }

}

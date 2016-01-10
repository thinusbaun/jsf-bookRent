package com.katner.beans;

import com.katner.model.AuthUser;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by michal on 09.01.16.
 */
@ManagedBean
@SessionScoped
public class UserAdminBean {
    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<AuthUser> getAllUsers() {
        return em.createQuery("from AuthUser").getResultList();
    }

    public void userActiveSettingListener(AjaxBehaviorEvent event) {
        Integer userid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userid"));
        Boolean value = (Boolean) ((UIOutput) event.getSource()).getValue();
        em.getTransaction().begin();
        AuthUser user = em.find(AuthUser.class, userid);
        user.setActive(value);
        em.merge(user);
        em.getTransaction().commit();
    }

    public void userIsStaffSettingListener(AjaxBehaviorEvent event) {
        Integer userid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userid"));
        Boolean value = (Boolean) ((UIOutput) event.getSource()).getValue();
        em.getTransaction().begin();
        AuthUser user = em.find(AuthUser.class, userid);
        user.setStaff(value);
        em.merge(user);
        em.getTransaction().commit();
    }

    public void userIsSuperuserSettingListener(AjaxBehaviorEvent event) {
        Integer userid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userid"));
        Boolean value = (Boolean) ((UIOutput) event.getSource()).getValue();
        em.getTransaction().begin();
        AuthUser user = em.find(AuthUser.class, userid);
        user.setSuperuser(value);
        em.merge(user);
        em.getTransaction().commit();
    }
}

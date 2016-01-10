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
//        FacesContext context = FacesContext.getCurrentInstance();
//        Map map = context.getExternalContext().getRequestParameterMap();
//        Integer userid = Integer.parseInt((String) map.get("userid"));
        Integer userid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userid"));
        Boolean value = (Boolean) ((UIOutput) event.getSource()).getValue();
//        Session session = em.unwrap(Session.class);
        em.getTransaction().begin();
        AuthUser user = em.find(AuthUser.class, userid);
//        AuthUser user = session.load(AuthUser.class, userid);
        user.setActive(value);
//        session.merge(user);
        em.merge(user);
        em.getTransaction().commit();
    }
}

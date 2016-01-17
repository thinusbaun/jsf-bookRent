package com.katner.beans;

import com.katner.model.AdminMessage;

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
 * Created by michal on 17.01.16.
 */
@ManagedBean
@SessionScoped
public class MessagesAdminBean {
    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private List<AdminMessage> allMessages;
    private String newContent;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    public List<AdminMessage> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(List<AdminMessage> allMessages) {
        this.allMessages = allMessages;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @PostConstruct
    public void init() {
        allMessages = em.createQuery("from AdminMessage").getResultList();
    }

    public String viewMessages() {
        allMessages = em.createQuery("from AdminMessage").getResultList();
        return "admin/messagesAdmin";
    }

    public String removeMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt((String) map.get("messageid"));
        try {
            em.getTransaction().begin();
            AdminMessage message = em.find(AdminMessage.class, id);
            em.remove(message);
            em.getTransaction().commit();
            allMessages.removeIf(a -> a.getId() == id);
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return "";
    }

    public String addMessage() {
        try {
            em.getTransaction().begin();
            AdminMessage message = new AdminMessage();
            message.setContent(newContent);
            message.setUserId(userBean.getUser().getId());
            message.setDate(new Date(Calendar.getInstance().getTime().getTime()));
            em.persist(message);
            em.getTransaction().commit();
            allMessages.add(message);
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return "";
    }
}

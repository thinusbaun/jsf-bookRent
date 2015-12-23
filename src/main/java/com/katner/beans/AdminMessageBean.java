package com.katner.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.katner.model.AdminMessage;

/**
 * Created by michal on 23.12.15.
 */
public class AdminMessageBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM AdminMessage AS o";

    private AdminMessage myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public AdminMessageBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public AdminMessage getEntity() {
        return myEntity;
    }

    public void setEntity(AdminMessage entity) {
        myEntity = entity;
    }

    // add new AdminMessage
    public String create() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "adminMessageList";
    }

    // save edited AdminMessage
    public String save() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            myEntity = entityManager.merge(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();
        return "adminMessageList";
    }

    // delete AdminMessage
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            AdminMessage entity = getCurrentEntity();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "adminMessageList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<AdminMessage> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<AdminMessage>(entities));
    }

    public String startCreate() {
        myEntity = new AdminMessage();
        return "createAdminMessage";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewAdminMessage";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editAdminMessage";
    }

    public AdminMessage getCurrentEntity() {
        AdminMessage entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public AdminMessage getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        AdminMessage entity = (AdminMessage) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public AdminMessage findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        AdminMessage entity = entityManager.find(AdminMessage.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<AdminMessage> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (AdminMessage entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<AdminMessage> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<AdminMessage> entities = (List<AdminMessage>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

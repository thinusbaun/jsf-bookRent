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

import com.katner.model.AuthPermission;

/**
 * Created by michal on 23.12.15.
 */
public class AuthPermissionBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM AuthPermission AS o";

    private AuthPermission myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public AuthPermissionBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public AuthPermission getEntity() {
        return myEntity;
    }

    public void setEntity(AuthPermission entity) {
        myEntity = entity;
    }

    // add new AuthPermission
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

        return "authPermissionList";
    }

    // save edited AuthPermission
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
        return "authPermissionList";
    }

    // delete AuthPermission
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            AuthPermission entity = getCurrentEntity();
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

        return "authPermissionList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<AuthPermission> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<AuthPermission>(entities));
    }

    public String startCreate() {
        myEntity = new AuthPermission();
        return "createAuthPermission";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewAuthPermission";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editAuthPermission";
    }

    public AuthPermission getCurrentEntity() {
        AuthPermission entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public AuthPermission getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        AuthPermission entity = (AuthPermission) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public AuthPermission findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        AuthPermission entity = entityManager.find(AuthPermission.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<AuthPermission> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (AuthPermission entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<AuthPermission> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<AuthPermission> entities = (List<AuthPermission>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

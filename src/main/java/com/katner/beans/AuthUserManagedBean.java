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

import com.katner.model.AuthUser;

/**
 * Created by michal on 21.12.15.
 */
public class AuthUserManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM AuthUser AS o";

    private AuthUser myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public AuthUserManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public AuthUser getEntity() {
        return myEntity;
    }

    public void setEntity(AuthUser entity) {
        myEntity = entity;
    }

    // add new AuthUser
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

        return "authUserList";
    }

    // save edited AuthUser
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
        return "authUserList";
    }

    // delete AuthUser
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            AuthUser entity = getCurrentEntity();
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

        return "authUserList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<AuthUser> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<AuthUser>(entities));
    }

    public String startCreate() {
        myEntity = new AuthUser();
        return "createAuthUser";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewAuthUser";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editAuthUser";
    }

    public AuthUser getCurrentEntity() {
        AuthUser entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public AuthUser getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        AuthUser entity = (AuthUser) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public AuthUser findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        AuthUser entity = entityManager.find(AuthUser.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<AuthUser> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (AuthUser entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<AuthUser> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<AuthUser> entities = (List<AuthUser>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

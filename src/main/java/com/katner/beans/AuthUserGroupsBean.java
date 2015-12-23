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

import com.katner.model.AuthUserGroups;

/**
 * Created by michal on 23.12.15.
 */
public class AuthUserGroupsBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM AuthUserGroups AS o";

    private AuthUserGroups myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public AuthUserGroupsBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public AuthUserGroups getEntity() {
        return myEntity;
    }

    public void setEntity(AuthUserGroups entity) {
        myEntity = entity;
    }

    // add new AuthUserGroups
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

        return "authUserGroupsList";
    }

    // save edited AuthUserGroups
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
        return "authUserGroupsList";
    }

    // delete AuthUserGroups
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            AuthUserGroups entity = getCurrentEntity();
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

        return "authUserGroupsList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<AuthUserGroups> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<AuthUserGroups>(entities));
    }

    public String startCreate() {
        myEntity = new AuthUserGroups();
        return "createAuthUserGroups";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewAuthUserGroups";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editAuthUserGroups";
    }

    public AuthUserGroups getCurrentEntity() {
        AuthUserGroups entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public AuthUserGroups getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        AuthUserGroups entity = (AuthUserGroups) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public AuthUserGroups findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        AuthUserGroups entity = entityManager.find(AuthUserGroups.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<AuthUserGroups> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (AuthUserGroups entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<AuthUserGroups> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<AuthUserGroups> entities = (List<AuthUserGroups>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

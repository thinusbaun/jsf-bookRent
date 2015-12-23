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

import com.katner.model.AuthGroupPermissions;

/**
 * Created by michal on 21.12.15.
 */
public class AuthGroupPermissionsManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM AuthGroupPermissions AS o";

    private AuthGroupPermissions myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public AuthGroupPermissionsManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public AuthGroupPermissions getEntity() {
        return myEntity;
    }

    public void setEntity(AuthGroupPermissions entity) {
        myEntity = entity;
    }

    // add new AuthGroupPermissions
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

        return "authGroupPermissionsList";
    }

    // save edited AuthGroupPermissions
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
        return "authGroupPermissionsList";
    }

    // delete AuthGroupPermissions
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            AuthGroupPermissions entity = getCurrentEntity();
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

        return "authGroupPermissionsList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<AuthGroupPermissions> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<AuthGroupPermissions>(entities));
    }

    public String startCreate() {
        myEntity = new AuthGroupPermissions();
        return "createAuthGroupPermissions";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewAuthGroupPermissions";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editAuthGroupPermissions";
    }

    public AuthGroupPermissions getCurrentEntity() {
        AuthGroupPermissions entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public AuthGroupPermissions getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        AuthGroupPermissions entity = (AuthGroupPermissions) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public AuthGroupPermissions findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        AuthGroupPermissions entity = entityManager.find(AuthGroupPermissions.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<AuthGroupPermissions> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (AuthGroupPermissions entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<AuthGroupPermissions> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<AuthGroupPermissions> entities = (List<AuthGroupPermissions>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

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

import com.katner.model.AuthUserUserPermissions;

/**
 * Created by michal on 23.12.15.
 */
public class AuthUserUserPermissionsBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM AuthUserUserPermissions AS o";

    private AuthUserUserPermissions myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public AuthUserUserPermissionsBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public AuthUserUserPermissions getEntity() {
        return myEntity;
    }

    public void setEntity(AuthUserUserPermissions entity) {
        myEntity = entity;
    }

    // add new AuthUserUserPermissions
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

        return "authUserUserPermissionsList";
    }

    // save edited AuthUserUserPermissions
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
        return "authUserUserPermissionsList";
    }

    // delete AuthUserUserPermissions
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            AuthUserUserPermissions entity = getCurrentEntity();
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

        return "authUserUserPermissionsList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<AuthUserUserPermissions> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<AuthUserUserPermissions>(entities));
    }

    public String startCreate() {
        myEntity = new AuthUserUserPermissions();
        return "createAuthUserUserPermissions";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewAuthUserUserPermissions";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editAuthUserUserPermissions";
    }

    public AuthUserUserPermissions getCurrentEntity() {
        AuthUserUserPermissions entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public AuthUserUserPermissions getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        AuthUserUserPermissions entity = (AuthUserUserPermissions) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public AuthUserUserPermissions findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        AuthUserUserPermissions entity = entityManager.find(AuthUserUserPermissions.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<AuthUserUserPermissions> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (AuthUserUserPermissions entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<AuthUserUserPermissions> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<AuthUserUserPermissions> entities = (List<AuthUserUserPermissions>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

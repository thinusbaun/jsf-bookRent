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

import com.katner.model.AuthGroup;

/**
 * Created by michal on 21.12.15.
 */
public class AuthGroupManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM AuthGroup AS o";

    private AuthGroup myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public AuthGroupManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public AuthGroup getEntity() {
        return myEntity;
    }

    public void setEntity(AuthGroup entity) {
        myEntity = entity;
    }

    // add new AuthGroup
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

        return "authGroupList";
    }

    // save edited AuthGroup
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
        return "authGroupList";
    }

    // delete AuthGroup
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            AuthGroup entity = getCurrentEntity();
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

        return "authGroupList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<AuthGroup> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<AuthGroup>(entities));
    }

    public String startCreate() {
        myEntity = new AuthGroup();
        return "createAuthGroup";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewAuthGroup";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editAuthGroup";
    }

    public AuthGroup getCurrentEntity() {
        AuthGroup entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public AuthGroup getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        AuthGroup entity = (AuthGroup) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public AuthGroup findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        AuthGroup entity = entityManager.find(AuthGroup.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<AuthGroup> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (AuthGroup entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<AuthGroup> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<AuthGroup> entities = (List<AuthGroup>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

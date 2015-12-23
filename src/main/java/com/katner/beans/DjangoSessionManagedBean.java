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

import com.katner.model.DjangoSession;

/**
 * Created by michal on 21.12.15.
 */
public class DjangoSessionManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM DjangoSession AS o";

    private DjangoSession myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public DjangoSessionManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public DjangoSession getEntity() {
        return myEntity;
    }

    public void setEntity(DjangoSession entity) {
        myEntity = entity;
    }

    // add new DjangoSession
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

        return "djangoSessionList";
    }

    // save edited DjangoSession
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
        return "djangoSessionList";
    }

    // delete DjangoSession
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            DjangoSession entity = getCurrentEntity();
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

        return "djangoSessionList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<DjangoSession> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<DjangoSession>(entities));
    }

    public String startCreate() {
        myEntity = new DjangoSession();
        return "createDjangoSession";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewDjangoSession";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editDjangoSession";
    }

    public DjangoSession getCurrentEntity() {
        DjangoSession entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public DjangoSession getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        DjangoSession entity = (DjangoSession) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public DjangoSession findEntity(String id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        DjangoSession entity = entityManager.find(DjangoSession.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<DjangoSession> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (DjangoSession entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<DjangoSession> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<DjangoSession> entities = (List<DjangoSession>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

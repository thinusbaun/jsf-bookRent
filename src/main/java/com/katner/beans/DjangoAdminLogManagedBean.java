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

import com.katner.model.DjangoAdminLog;

/**
 * Created by michal on 21.12.15.
 */
public class DjangoAdminLogManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM DjangoAdminLog AS o";

    private DjangoAdminLog myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public DjangoAdminLogManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public DjangoAdminLog getEntity() {
        return myEntity;
    }

    public void setEntity(DjangoAdminLog entity) {
        myEntity = entity;
    }

    // add new DjangoAdminLog
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

        return "djangoAdminLogList";
    }

    // save edited DjangoAdminLog
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
        return "djangoAdminLogList";
    }

    // delete DjangoAdminLog
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            DjangoAdminLog entity = getCurrentEntity();
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

        return "djangoAdminLogList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<DjangoAdminLog> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<DjangoAdminLog>(entities));
    }

    public String startCreate() {
        myEntity = new DjangoAdminLog();
        return "createDjangoAdminLog";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewDjangoAdminLog";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editDjangoAdminLog";
    }

    public DjangoAdminLog getCurrentEntity() {
        DjangoAdminLog entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public DjangoAdminLog getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        DjangoAdminLog entity = (DjangoAdminLog) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public DjangoAdminLog findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        DjangoAdminLog entity = entityManager.find(DjangoAdminLog.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<DjangoAdminLog> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (DjangoAdminLog entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<DjangoAdminLog> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<DjangoAdminLog> entities = (List<DjangoAdminLog>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

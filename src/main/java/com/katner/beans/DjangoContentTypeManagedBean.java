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

import com.katner.model.DjangoContentType;

/**
 * Created by michal on 21.12.15.
 */
public class DjangoContentTypeManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM DjangoContentType AS o";

    private DjangoContentType myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public DjangoContentTypeManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public DjangoContentType getEntity() {
        return myEntity;
    }

    public void setEntity(DjangoContentType entity) {
        myEntity = entity;
    }

    // add new DjangoContentType
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

        return "djangoContentTypeList";
    }

    // save edited DjangoContentType
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
        return "djangoContentTypeList";
    }

    // delete DjangoContentType
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            DjangoContentType entity = getCurrentEntity();
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

        return "djangoContentTypeList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<DjangoContentType> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<DjangoContentType>(entities));
    }

    public String startCreate() {
        myEntity = new DjangoContentType();
        return "createDjangoContentType";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewDjangoContentType";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editDjangoContentType";
    }

    public DjangoContentType getCurrentEntity() {
        DjangoContentType entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public DjangoContentType getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        DjangoContentType entity = (DjangoContentType) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public DjangoContentType findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        DjangoContentType entity = entityManager.find(DjangoContentType.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<DjangoContentType> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (DjangoContentType entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<DjangoContentType> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<DjangoContentType> entities = (List<DjangoContentType>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

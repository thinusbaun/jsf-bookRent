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

import com.katner.model.BookTags;

/**
 * Created by michal on 21.12.15.
 */
public class BookTagsManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM BookTags AS o";

    private BookTags myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public BookTagsManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public BookTags getEntity() {
        return myEntity;
    }

    public void setEntity(BookTags entity) {
        myEntity = entity;
    }

    // add new BookTags
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

        return "bookTagsList";
    }

    // save edited BookTags
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
        return "bookTagsList";
    }

    // delete BookTags
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BookTags entity = getCurrentEntity();
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

        return "bookTagsList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<BookTags> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<BookTags>(entities));
    }

    public String startCreate() {
        myEntity = new BookTags();
        return "createBookTags";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewBookTags";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editBookTags";
    }

    public BookTags getCurrentEntity() {
        BookTags entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public BookTags getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        BookTags entity = (BookTags) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public BookTags findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        BookTags entity = entityManager.find(BookTags.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<BookTags> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (BookTags entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<BookTags> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<BookTags> entities = (List<BookTags>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

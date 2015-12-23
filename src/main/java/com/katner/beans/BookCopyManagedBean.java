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

import com.katner.model.BookCopy;

/**
 * Created by michal on 21.12.15.
 */
public class BookCopyManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM BookCopy AS o";

    private BookCopy myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public BookCopyManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public BookCopy getEntity() {
        return myEntity;
    }

    public void setEntity(BookCopy entity) {
        myEntity = entity;
    }

    // add new BookCopy
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

        return "bookCopyList";
    }

    // save edited BookCopy
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
        return "bookCopyList";
    }

    // delete BookCopy
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BookCopy entity = getCurrentEntity();
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

        return "bookCopyList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<BookCopy> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<BookCopy>(entities));
    }

    public String startCreate() {
        myEntity = new BookCopy();
        return "createBookCopy";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewBookCopy";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editBookCopy";
    }

    public BookCopy getCurrentEntity() {
        BookCopy entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public BookCopy getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        BookCopy entity = (BookCopy) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public BookCopy findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        BookCopy entity = entityManager.find(BookCopy.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<BookCopy> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (BookCopy entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<BookCopy> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<BookCopy> entities = (List<BookCopy>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

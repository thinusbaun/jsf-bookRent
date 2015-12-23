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

import com.katner.model.BookAuthors;

/**
 * Created by michal on 23.12.15.
 */
public class BookAuthorsBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM BookAuthors AS o";

    private BookAuthors myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public BookAuthorsBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public BookAuthors getEntity() {
        return myEntity;
    }

    public void setEntity(BookAuthors entity) {
        myEntity = entity;
    }

    // add new BookAuthors
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

        return "bookAuthorsList";
    }

    // save edited BookAuthors
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
        return "bookAuthorsList";
    }

    // delete BookAuthors
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BookAuthors entity = getCurrentEntity();
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

        return "bookAuthorsList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<BookAuthors> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<BookAuthors>(entities));
    }

    public String startCreate() {
        myEntity = new BookAuthors();
        return "createBookAuthors";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewBookAuthors";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editBookAuthors";
    }

    public BookAuthors getCurrentEntity() {
        BookAuthors entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public BookAuthors getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        BookAuthors entity = (BookAuthors) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public BookAuthors findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        BookAuthors entity = entityManager.find(BookAuthors.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<BookAuthors> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (BookAuthors entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<BookAuthors> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<BookAuthors> entities = (List<BookAuthors>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

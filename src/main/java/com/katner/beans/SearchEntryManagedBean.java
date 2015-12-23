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

import com.katner.model.SearchEntry;

/**
 * Created by michal on 21.12.15.
 */
public class SearchEntryManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM SearchEntry AS o";

    private SearchEntry myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public SearchEntryManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public SearchEntry getEntity() {
        return myEntity;
    }

    public void setEntity(SearchEntry entity) {
        myEntity = entity;
    }

    // add new SearchEntry
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

        return "searchEntryList";
    }

    // save edited SearchEntry
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
        return "searchEntryList";
    }

    // delete SearchEntry
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            SearchEntry entity = getCurrentEntity();
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

        return "searchEntryList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<SearchEntry> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<SearchEntry>(entities));
    }

    public String startCreate() {
        myEntity = new SearchEntry();
        return "createSearchEntry";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewSearchEntry";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editSearchEntry";
    }

    public SearchEntry getCurrentEntity() {
        SearchEntry entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public SearchEntry getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        SearchEntry entity = (SearchEntry) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public SearchEntry findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        SearchEntry entity = entityManager.find(SearchEntry.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<SearchEntry> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (SearchEntry entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<SearchEntry> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<SearchEntry> entities = (List<SearchEntry>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

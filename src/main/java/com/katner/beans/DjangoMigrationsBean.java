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

import com.katner.model.DjangoMigrations;

/**
 * Created by michal on 23.12.15.
 */
public class DjangoMigrationsBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM DjangoMigrations AS o";

    private DjangoMigrations myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public DjangoMigrationsBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public DjangoMigrations getEntity() {
        return myEntity;
    }

    public void setEntity(DjangoMigrations entity) {
        myEntity = entity;
    }

    // add new DjangoMigrations
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

        return "djangoMigrationsList";
    }

    // save edited DjangoMigrations
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
        return "djangoMigrationsList";
    }

    // delete DjangoMigrations
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            DjangoMigrations entity = getCurrentEntity();
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

        return "djangoMigrationsList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<DjangoMigrations> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<DjangoMigrations>(entities));
    }

    public String startCreate() {
        myEntity = new DjangoMigrations();
        return "createDjangoMigrations";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewDjangoMigrations";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editDjangoMigrations";
    }

    public DjangoMigrations getCurrentEntity() {
        DjangoMigrations entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public DjangoMigrations getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        DjangoMigrations entity = (DjangoMigrations) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public DjangoMigrations findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        DjangoMigrations entity = entityManager.find(DjangoMigrations.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<DjangoMigrations> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (DjangoMigrations entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<DjangoMigrations> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<DjangoMigrations> entities = (List<DjangoMigrations>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}

package com.katner.beans;

import com.katner.model.Book;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by michal on 23.12.15.
 */
@ManagedBean
@SessionScoped
public class BookListBean {
    @ManagedProperty("#{entityManagerFactoryBean.entityManager}")
    private EntityManager em;
    private Integer authorId = -1;
    private Integer tagId = -1;
    private List<Book> books;
    private String searchQuery;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @PostConstruct
    public void init() {
        books = em.createQuery("from Book ").getResultList();
        authorId = -1;
        tagId = -1;
    }

    public String getAllBooks() {
        books = em.createQuery("from Book ").getResultList();
        return "listBooks";
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String booksByAuthor() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String aid = (String) map.get("authorId");
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Book.class, "book").createAlias("authors", "a").createAlias("tags", "t");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (aid != null) {
            criteria.add(Restrictions.eq("a.id", Integer.parseInt(aid)));
        }
        books = criteria.list();
        return "listBooks";
    }

    public String booksByTag() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String tid = (String) map.get("tagId");
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Book.class, "book").createAlias("authors", "a").createAlias("tags", "t");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (tid != null) {
            criteria.add(Restrictions.eq("t.id", Integer.parseInt(tid)));
        }
        books = criteria.list();
        return "listBooks";
    }

    public String search() {
        ArrayList<String> searchFields = new ArrayList<String>();
        searchQuery = searchQuery.replaceAll("author:", "authors.name:");
        searchQuery = searchQuery.replaceAll("tag:", "tags.title:");

        searchFields.add("title");
        searchFields.add("isbn");
        searchFields.add("authors.name");
        searchFields.add("tags.title");
        if (searchQuery != null) {
            FullTextEntityManager fullTextEntityManager =
                    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
            em.getTransaction().begin();
            QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                    .buildQueryBuilder().forEntity(Book.class).get();

            org.apache.lucene.search.Query luceneQuery = null;
            try {
                MultiFieldQueryParser parser = new MultiFieldQueryParser(searchFields.toArray(new String[searchFields.size()]),
                        new SimpleAnalyzer());
                parser.setDefaultOperator(QueryParser.Operator.OR);
                luceneQuery = parser.parse(searchQuery);
            } catch (ParseException e) {
                e.printStackTrace(); //TODO: zwracać error do jsp
                em.getTransaction().rollback();
            }


            Query jpaQuery =
                    fullTextEntityManager.createFullTextQuery(luceneQuery, Book.class);

            books = jpaQuery.getResultList();

            em.getTransaction().commit();

        }
        searchQuery = "";
        return "listBooks";
    }

    public List<Book> getNewBooks() {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Book.class).addOrder(Order.desc("addedDate"));
        return criteria.setMaxResults(5).list();
    }

    public Book searchBook(Integer id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }


}

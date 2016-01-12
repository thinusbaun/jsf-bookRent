package com.katner.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 21.12.15.
 */
@Entity
@Indexed
@Table(name = "book")
public class Book {
    @Transient
    public List<String> sAuthors;
    @Transient
    public List<String> sTags;
    private int id;
    private String title;
    private String isbn;
    private Date addedDate;
    private List<Author> authors;
    private List<Tag> tags;
    private List<BookCopy> copies;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "isbn", nullable = false, length = 15)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "addedDate", nullable = false)
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (addedDate != null ? !addedDate.equals(book.addedDate) : book.addedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (addedDate != null ? addedDate.hashCode() : 0);
        return result;
    }

    @ManyToMany()
    @JoinTable(name = "book_authors", catalog = "", schema = "wypozyczalnia", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false))
    @IndexedEmbedded
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @ManyToMany
    @JoinTable(name = "book_tags", catalog = "", schema = "wypozyczalnia", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false))
    @IndexedEmbedded
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @OneToMany(mappedBy = "book")
    public List<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

    @Transient
    public String getAvailableCopiesCount() {
        Integer allCopies = copies.size();
        Integer availableCopies = allCopies;
        for (BookCopy copy : copies) {
            for (Rental rental : copy.getRentals()) {
                if (rental.getReturnDate() == null) {
                    availableCopies--;
                }
            }
        }
        return availableCopies.toString() + " / " + allCopies.toString();
    }

    @Transient
    public List<String> getSelectedAuthors() {
        List<String> selectedAuthors = new ArrayList<String>();
        try {
            for (Author author : authors) {
                selectedAuthors.add(new Integer(author.getId()).toString());
            }
        } catch (Exception e) {
        }
        return selectedAuthors;
    }

    @Transient
    public void setSelectedAuthors(List<String> a) {
        this.sAuthors = a;
    }

    @Transient
    public List<String> getSelectedTags() {
        List<String> selectedTags = new ArrayList<String>();
        try {
            for (Tag tag : tags) {
                selectedTags.add(new Integer(tag.getId()).toString());
            }
        } catch (Exception e) {

        }
        return selectedTags;
    }

    @Transient
    public void setSelectedTags(List<String> t) {
        this.sTags = t;
    }
}

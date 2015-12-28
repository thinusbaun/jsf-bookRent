package com.katner.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by michal on 21.12.15.
 */
@Entity
@Table(name="book")
public class Book {
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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
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
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @ManyToMany
    @JoinTable(name = "book_tags", catalog = "", schema = "wypozyczalnia", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false))
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
    public String getAvailableCopiesCount()
    {
        Integer allCopies = copies.size();
        Integer availableCopies = allCopies;
        for (BookCopy copy : copies)
        {
            for(Rental rental : copy.getRentals())
            {
                if (rental.getReturnDate() == null)
                {
                    availableCopies--;
                }
            }
        }
        return availableCopies.toString() + " / " + allCopies.toString();
    }
}

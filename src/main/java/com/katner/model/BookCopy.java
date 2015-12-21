package com.katner.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by michal on 21.12.15.
 */
@Entity
public class BookCopy {
    private int id;
    private int bookId;
    private Book book;
    private List<Rental> rentals;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookId", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCopy bookCopy = (BookCopy) o;

        if (id != bookCopy.id) return false;
        if (bookId != bookCopy.bookId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id", nullable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @OneToMany(mappedBy = "bookCopy")
    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
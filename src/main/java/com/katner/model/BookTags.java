package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 21.12.15.
 */
@Entity
@Table(name = "book_tags", schema = "wypozyczalnia", catalog = "")
public class BookTags {
    private int id;
    private int bookId;
    private int tagId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "tag_id", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookTags bookTags = (BookTags) o;

        if (id != bookTags.id) return false;
        if (bookId != bookTags.bookId) return false;
        if (tagId != bookTags.tagId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookId;
        result = 31 * result + tagId;
        return result;
    }
}

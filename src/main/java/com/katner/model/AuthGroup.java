package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 21.12.15.
 */
@Entity
@Table(name = "auth_group", schema = "wypozyczalnia", catalog = "")
public class AuthGroup {
    private int id;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 80)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthGroup authGroup = (AuthGroup) o;

        if (id != authGroup.id) return false;
        if (name != null ? !name.equals(authGroup.name) : authGroup.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

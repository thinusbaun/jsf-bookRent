package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 21.12.15.
 */
@Entity
@Table(name = "auth_user_user_permissions", schema = "wypozyczalnia", catalog = "")
public class AuthUserUserPermissions {
    private int id;
    private int userId;
    private int permissionId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "permission_id", nullable = false)
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUserUserPermissions that = (AuthUserUserPermissions) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (permissionId != that.permissionId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + permissionId;
        return result;
    }
}

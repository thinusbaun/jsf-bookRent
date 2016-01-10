package com.katner.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by michal on 21.12.15.
 */
@Entity
@Table(name = "auth_user", schema = "wypozyczalnia", catalog = "")
public class AuthUser {
    private int id;
    private String password;
    private Date lastLogin;
    private byte isSuperuser;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private byte isStaff;
    private byte isActive;
    private Date dateJoined;
    private List<Rental> rentals;
    private List<SearchEntry> searchEntries;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 128)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "last_login", nullable = true)
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "is_superuser", nullable = false)
    public byte getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(byte isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    @Transient
    public boolean isSuperuser() {
        return (isSuperuser == 1);
    }

    @Transient
    public void setSuperuser(boolean superuser) {
        if (superuser) {
            this.isSuperuser = 1;
        } else {
            this.isSuperuser = 0;
        }
    }

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 254)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "is_staff", nullable = false)
    public byte getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(byte isStaff) {
        this.isStaff = isStaff;
    }

    @Transient
    public boolean isStaff() {
        return (isStaff == 1);
    }

    @Transient
    public void setStaff(boolean staff) {
        if (staff) {
            this.isStaff = 1;
        } else {
            this.isStaff = 0;
        }
    }

    @Basic
    @Column(name = "is_active", nullable = false)
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Transient
    public boolean isActive() {
        return (isActive == 1);
    }

    @Transient
    public void setActive(boolean active) {
        if (active) {
            this.isActive = 1;
        } else {
            this.isActive = 0;
        }
    }

    @Basic
    @Column(name = "date_joined", nullable = false)
    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUser authUser = (AuthUser) o;

        if (id != authUser.id) return false;
        if (isSuperuser != authUser.isSuperuser) return false;
        if (isStaff != authUser.isStaff) return false;
        if (isActive != authUser.isActive) return false;
        if (password != null ? !password.equals(authUser.password) : authUser.password != null) return false;
        if (lastLogin != null ? !lastLogin.equals(authUser.lastLogin) : authUser.lastLogin != null) return false;
        if (username != null ? !username.equals(authUser.username) : authUser.username != null) return false;
        if (firstName != null ? !firstName.equals(authUser.firstName) : authUser.firstName != null) return false;
        if (lastName != null ? !lastName.equals(authUser.lastName) : authUser.lastName != null) return false;
        if (email != null ? !email.equals(authUser.email) : authUser.email != null) return false;
        if (dateJoined != null ? !dateJoined.equals(authUser.dateJoined) : authUser.dateJoined != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (int) isSuperuser;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) isStaff;
        result = 31 * result + (int) isActive;
        result = 31 * result + (dateJoined != null ? dateJoined.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @OneToMany(mappedBy = "user")
    public List<SearchEntry> getSearchEntries() {
        return searchEntries;
    }

    public void setSearchEntries(List<SearchEntry> searchEntries) {
        this.searchEntries = searchEntries;
    }
}

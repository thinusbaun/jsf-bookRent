package katner.model;

import javax.persistence.*;

/**
 * Created by michal on 15.12.15.
 */
@Entity
@Table(name = "auth_user", schema = "wypozyczalnia", catalog = "")
public class AuthUser {
    private int id;
    private String password;
    private byte isSuperuser;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private byte isStaff;
    private byte isActive;

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
    @Column(name = "is_superuser", nullable = false)
    public byte getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(byte isSuperuser) {
        this.isSuperuser = isSuperuser;
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

    @Basic
    @Column(name = "is_active", nullable = false)
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
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
        if (username != null ? !username.equals(authUser.username) : authUser.username != null) return false;
        if (firstName != null ? !firstName.equals(authUser.firstName) : authUser.firstName != null) return false;
        if (lastName != null ? !lastName.equals(authUser.lastName) : authUser.lastName != null) return false;
        if (email != null ? !email.equals(authUser.email) : authUser.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) isSuperuser;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) isStaff;
        result = 31 * result + (int) isActive;
        return result;
    }
}

package com.katner.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by michal on 21.12.15.
 */
@Entity
@Table(name = "django_session", schema = "wypozyczalnia", catalog = "")
public class DjangoSession {
    private String sessionKey;
    private String sessionData;
    private Date expireDate;

    @Id
    @Column(name = "session_key", nullable = false, length = 40)
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Basic
    @Column(name = "session_data", nullable = false, length = -1)
    public String getSessionData() {
        return sessionData;
    }

    public void setSessionData(String sessionData) {
        this.sessionData = sessionData;
    }

    @Basic
    @Column(name = "expire_date", nullable = false)
    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DjangoSession that = (DjangoSession) o;

        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;
        if (sessionData != null ? !sessionData.equals(that.sessionData) : that.sessionData != null) return false;
        if (expireDate != null ? !expireDate.equals(that.expireDate) : that.expireDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey != null ? sessionKey.hashCode() : 0;
        result = 31 * result + (sessionData != null ? sessionData.hashCode() : 0);
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        return result;
    }
}

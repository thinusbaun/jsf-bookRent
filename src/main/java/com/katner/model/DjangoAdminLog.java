package com.katner.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by michal on 21.12.15.
 */
@Entity
@Table(name = "django_admin_log", schema = "wypozyczalnia", catalog = "")
public class DjangoAdminLog {
    private int id;
    private Date actionTime;
    private String objectId;
    private String objectRepr;
    private short actionFlag;
    private String changeMessage;
    private Integer contentTypeId;
    private int userId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "action_time", nullable = false)
    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    @Basic
    @Column(name = "object_id", nullable = true, length = -1)
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "object_repr", nullable = false, length = 200)
    public String getObjectRepr() {
        return objectRepr;
    }

    public void setObjectRepr(String objectRepr) {
        this.objectRepr = objectRepr;
    }

    @Basic
    @Column(name = "action_flag", nullable = false)
    public short getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(short actionFlag) {
        this.actionFlag = actionFlag;
    }

    @Basic
    @Column(name = "change_message", nullable = false, length = -1)
    public String getChangeMessage() {
        return changeMessage;
    }

    public void setChangeMessage(String changeMessage) {
        this.changeMessage = changeMessage;
    }

    @Basic
    @Column(name = "content_type_id", nullable = true)
    public Integer getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(Integer contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DjangoAdminLog that = (DjangoAdminLog) o;

        if (id != that.id) return false;
        if (actionFlag != that.actionFlag) return false;
        if (userId != that.userId) return false;
        if (actionTime != null ? !actionTime.equals(that.actionTime) : that.actionTime != null) return false;
        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;
        if (objectRepr != null ? !objectRepr.equals(that.objectRepr) : that.objectRepr != null) return false;
        if (changeMessage != null ? !changeMessage.equals(that.changeMessage) : that.changeMessage != null)
            return false;
        if (contentTypeId != null ? !contentTypeId.equals(that.contentTypeId) : that.contentTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (actionTime != null ? actionTime.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (objectRepr != null ? objectRepr.hashCode() : 0);
        result = 31 * result + (int) actionFlag;
        result = 31 * result + (changeMessage != null ? changeMessage.hashCode() : 0);
        result = 31 * result + (contentTypeId != null ? contentTypeId.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}

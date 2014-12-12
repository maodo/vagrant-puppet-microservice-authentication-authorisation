package com.example.session.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.lang.Override;
import java.lang.String;
import java.util.Date;
import java.util.UUID;

public class Session {

    @NotNull
    @JsonProperty
    private String uuid;

    @NotNull
    @JsonProperty
    private String user;

    @NotNull
    @JsonProperty
    private Date created;

    public Session() {}

    public Session(String user) {
        this.uuid = UUID.randomUUID().toString();
        this.user = user;
        this.created = new Date();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (created != null ? !created.equals(session.created) : session.created != null) return false;
        if (user != null ? !user.equals(session.user) : session.user != null) return false;
        if (uuid != null ? !uuid.equals(session.uuid) : session.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}

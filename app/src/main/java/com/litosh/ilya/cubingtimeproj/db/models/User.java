package com.litosh.ilya.cubingtimeproj.db.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * ObjectBox-сущность User для кеширования информации о пользователе
 *
 * Created by ilya_ on 20.06.2018.
 */
@Entity
public class User {

    @Id
    private long id;
    private String email;
    private String pass;
    private boolean active;
    private String phpSessId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhpSessId() {
        return phpSessId;
    }

    public void setPhpSessId(String phpSessId) {
        this.phpSessId = phpSessId;
    }
}

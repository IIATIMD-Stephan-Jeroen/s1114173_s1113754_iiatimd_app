package com.example.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String cookie;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public User(String name, String cookie){
        this.name = name;
        this.cookie = cookie;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}

package com.example.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String firstFavItem;

    @ColumnInfo
    private String secondFavItem;

    @PrimaryKey
    private int id;

    public User(String name, String firstFavItem, String secondFavItem, int id){
        this.name = name;
        this.firstFavItem = firstFavItem;
        this.secondFavItem = secondFavItem;
        this.id = id;
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

    public String getFirstFavItem() {
        return firstFavItem;
    }

    public void setFirstFavItem(String firstFavItem) {
        this.firstFavItem = firstFavItem;
    }

    public String getSecondFavItem() {
        return secondFavItem;
    }

    public void setSecondFavItem(String secondFavItem) {
        this.secondFavItem = secondFavItem;
    }
}

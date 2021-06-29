package com.example.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "cost")
    public String cost;

    @ColumnInfo(name = "currency")
    public String currency;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "weight")
    public String weight;

    @ColumnInfo(name = "damage")
    public String damage;

    @ColumnInfo(name = "damage_type")
    public String damage_type;

    @ColumnInfo(name = "property_1")
    public String property_1;

    @ColumnInfo(name = "property_2")
    public String property_2;

    @ColumnInfo(name = "property_3")
    public String property_3;

    @ColumnInfo(name = "property_4")
    public String property_4;


}

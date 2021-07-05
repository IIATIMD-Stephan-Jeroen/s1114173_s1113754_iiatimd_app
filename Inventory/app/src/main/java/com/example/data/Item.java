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

    @ColumnInfo(name = "community_item")
    public boolean community_item;

    public boolean isCommunity_item() {
        return community_item;
    }

    public void setCommunity_item(boolean community_item) {
        this.community_item = community_item;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCost() {
        return cost;
    }

    public String getWeight() {
        return weight;
    }

    public String getDamage() {
        return damage;
    }

    public String getDamage_type() {
        return damage_type;
    }

    public String getProperty_1() {
        return property_1;
    }

    public String getProperty_2() {
        return property_2;
    }

    public String getProperty_3() {
        return property_3;
    }

    public String getProperty_4() {
        return property_4;
    }
}

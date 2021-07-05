package com.example.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity
public class Bagitem {

    @PrimaryKey
    private int itemId;

    @ColumnInfo
    private int amount;

    @ColumnInfo
    private int belongsToBagId;

    public Bagitem(int itemId, int amount, int belongsToBagId){
        this.itemId = itemId;
        this.amount = amount;
        this.belongsToBagId = belongsToBagId;
    }

    public int getBelongsToBagId() {
        return belongsToBagId;
    }

    public void setBelongsToBagId(int belongsToBagId) {
        this.belongsToBagId = belongsToBagId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

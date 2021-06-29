package com.example.data;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ItemDAO {

    @Insert
    void InsertItem(Item item);
}

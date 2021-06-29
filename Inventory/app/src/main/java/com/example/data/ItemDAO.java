package com.example.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM item")
    List<Item> getAllItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Item... items);
}

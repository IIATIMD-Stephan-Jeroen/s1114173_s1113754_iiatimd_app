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

    @Query("SELECT * FROM item WHERE id = :item_id")
    Item getItemById(int item_id);

    @Query("SELECT * FROM item WHERE community_item = :state")
    List<Item> getAllItemConditionally(boolean state);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Item... items);
}

package com.example.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BagitemDAO {

    @Query("SELECT * FROM bagitem WHERE belongsToBagId = :bagId")
    List<Bagitem> getAllBagItems(int bagId);

    @Query("SELECT * FROM bagitem WHERE itemId = :id AND belongsToBagId = :bagId")
    Bagitem getBagItemById(int id, int bagId);

    @Delete
    void delete(Bagitem bagitem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBagItem(Bagitem bagitem);
}

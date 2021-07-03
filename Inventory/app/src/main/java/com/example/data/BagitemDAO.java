package com.example.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BagitemDAO {

    @Query("SELECT * FROM bagitem WHERE belongsToBagId = :bagId")
    List<Bagitem> getAllBagItems(int bagId);

    @Query("SELECT * FROM bagitem WHERE itemId = :id AND belongsToBagId = :bagId")
    Bagitem getBagItemById(int id, int bagId);

    @Query("UPDATE bagitem SET amount = :amount WHERE itemId = :id AND belongsToBagId = :bagId")
    void updateBagItemAmount(int amount, int id, int bagId);

    @Insert
    void insertBagItem(Bagitem bagitem);
}

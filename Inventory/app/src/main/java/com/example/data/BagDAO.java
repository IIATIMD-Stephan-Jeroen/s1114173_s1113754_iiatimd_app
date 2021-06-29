package com.example.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BagDAO {

    @Query("SELECT * FROM bag")
    List<Bag> getAllBags();

    @Insert
    void InsertBag(Bag bag);

    @Delete
    void delete(Bag bag);
}

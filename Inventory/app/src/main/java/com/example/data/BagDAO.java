package com.example.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BagDAO {

    @Query("SELECT * FROM bag")
    List<Bag> getAll();

    @Insert
    void InsertBag(Bag bag);

    @Delete
    void delete(Bag bag);
}

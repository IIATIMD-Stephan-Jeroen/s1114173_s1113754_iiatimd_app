package com.example.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Bag.class, Item.class, User.class}, version = 15)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemDAO itemDAO();
    public abstract BagDAO bagDAO();
    public abstract UserDAO userDAO();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context){
        return Room
                .databaseBuilder(context, AppDatabase.class, "InventoryDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}

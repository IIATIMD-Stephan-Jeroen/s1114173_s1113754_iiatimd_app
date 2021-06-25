package com.example.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Bag.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BagDAO bagDAO();

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
                .fallbackToDestructiveMigration()
                .build();
    }
}

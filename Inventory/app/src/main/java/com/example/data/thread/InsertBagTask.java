package com.example.data.thread;

import android.util.Log;

import androidx.room.Database;

import com.example.data.AppDatabase;
import com.example.data.Bag;

public class InsertBagTask implements Runnable{

    AppDatabase db;
    Bag bag;

    public InsertBagTask(AppDatabase db, Bag bag){
        this.db = db;
        this.bag = bag;
    }

    @Override
    public void run() {
        db.bagDAO().InsertBag(this.bag);
    }
}

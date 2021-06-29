package com.example.data.thread;

import com.example.data.AppDatabase;
import com.example.data.Bag;

public class DeleteBagTask implements Runnable{
    AppDatabase db;
    Bag bag;

    public DeleteBagTask(AppDatabase db, Bag bag){
        this.db = db;
        this.bag = bag;
    }

    @Override
    public void run() {
        db.bagDAO().delete(bag);
    }
}

package com.example.data.thread;

import com.example.data.AppDatabase;

public class GetBagTask implements Runnable{

    AppDatabase db;

    public GetBagTask(AppDatabase db){
        this.db = db;
    }

    @Override
    public void run() {

    }
}

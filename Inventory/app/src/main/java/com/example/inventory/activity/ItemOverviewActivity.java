package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.data.Item;
import com.example.data.ItemDatabase;
import com.example.inventory.R;
import com.example.inventory.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ItemOverviewActivity extends AppCompatActivity {

    private List<Item> items;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);

        items = getAllItems();

        RecyclerView recyclerView = findViewById(R.id.itemRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);

    }

    public List<Item> getAllItems() {
        ItemDatabase db = ItemDatabase.getDbInstance(this.getApplicationContext());
        return db.itemDao().getAllItems();
    }
}
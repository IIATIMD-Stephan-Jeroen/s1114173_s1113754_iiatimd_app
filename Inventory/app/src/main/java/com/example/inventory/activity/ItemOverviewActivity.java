package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.data.AppDatabase;
import com.example.data.Item;
import com.example.inventory.R;
import com.example.inventory.adapter.ItemAdapter;

import java.util.List;

public class ItemOverviewActivity extends AppCompatActivity {
    private RecyclerView itemRecyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);
        this.itemAdapter = new ItemAdapter(this.getApplicationContext());
        items = getAllItems();
        itemAdapter.setItems(items);
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d("Debug", "Initiated Recyclerview");

        itemRecyclerView = findViewById(R.id.itemRecyclerView);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerView.hasFixedSize();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemRecyclerView.addItemDecoration(dividerItemDecoration);


        itemRecyclerView.setAdapter(itemAdapter);
    }

    public List<Item> getAllItems() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        return db.itemDAO().getAllItems();
    }
}
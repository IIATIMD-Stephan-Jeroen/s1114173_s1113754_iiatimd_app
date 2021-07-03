package com.example.inventory.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Adapter;
import android.widget.EditText;

import com.example.data.AppDatabase;
import com.example.data.Item;
import com.example.inventory.R;
import com.example.inventory.adapter.BagitemAdapter;
import com.example.inventory.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ItemOverviewActivity extends AppCompatActivity {
    private RecyclerView itemRecyclerView;
    private ItemAdapter itemAdapter;
    private BagitemAdapter bagitemAdapter;
    private List<Item> items;
    private Context mContext;

    private int bagId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);

        mContext = this.getApplicationContext();


        Intent intent = getIntent();
        bagId = Integer.valueOf(intent.getStringExtra("bag_id"));
        this.itemAdapter = new ItemAdapter(mContext, String.valueOf(bagId));
        this.bagitemAdapter = new BagitemAdapter(mContext, bagId);

        items = getAllItems();
        itemAdapter.setItems(items);
        initRecyclerView();
        EditText editText = findViewById(R.id.editTextItemName);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

    }

    private void filter(String text) {
        List<Item> filteredList = new ArrayList<>();

        for (Item item : items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if(filteredList != null) {
            itemAdapter.filterList(filteredList);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        itemAdapter.setItems(getAllItems());
    }



    private void initRecyclerView(){

        itemRecyclerView = findViewById(R.id.itemRecyclerView);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerView.hasFixedSize();

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        itemRecyclerView.addItemDecoration(dividerItemDecoration);


        itemRecyclerView.setAdapter(itemAdapter);
    }

    public List<Item> getAllItems() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        return db.itemDAO().getAllItems();
    }
}
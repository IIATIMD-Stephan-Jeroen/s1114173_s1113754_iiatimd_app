package com.example.inventory.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data.AppDatabase;
import com.example.data.Bagitem;
import com.example.data.Item;
import com.example.inventory.R;
import com.example.inventory.adapter.BagitemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BagInventoryActivity extends AppCompatActivity implements View.OnClickListener {

    TextView inventoryHolder;
    FloatingActionButton addItemToBagButton;
    private RecyclerView bagItemRecyclerView;
    private BagitemAdapter bagitemAdapter;
    private String bagId;
    private String bagName;
    private List<Bagitem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_inventory);

        inventoryHolder = findViewById(R.id.inventoryHolder);
        addItemToBagButton = findViewById(R.id.addItemToBagButton);
        this.bagitemAdapter = new BagitemAdapter(this.getApplicationContext());

        Intent intent = getIntent();
        this.bagName = intent.getStringExtra("bag_name");
        this.bagId = intent.getStringExtra("bag_id");

        items = getAllItems();
        bagitemAdapter.setItems(items);
        initRecyclerView();



        Log.e("test","BagInventoryActivity : " + bagId);
        inventoryHolder.setText(bagName);
        addItemToBagButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ItemOverviewActivity.class);
        intent.putExtra("bag_id", bagId);
        startActivity(intent);
    }


    private void initRecyclerView(){

        bagItemRecyclerView = findViewById(R.id.bagItemRecyclerView);
        bagItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bagItemRecyclerView.hasFixedSize();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        bagItemRecyclerView.addItemDecoration(dividerItemDecoration);


        bagItemRecyclerView.setAdapter(bagitemAdapter);
    }

    public List<Bagitem> getAllItems() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        return db.bagitemDAO().getAllBagItems(Integer.valueOf(bagId));
    }

    // code for the top bar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.inventory_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
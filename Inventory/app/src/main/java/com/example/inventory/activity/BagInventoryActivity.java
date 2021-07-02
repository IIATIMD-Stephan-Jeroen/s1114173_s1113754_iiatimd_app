package com.example.inventory.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventory.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BagInventoryActivity extends AppCompatActivity implements View.OnClickListener {

    TextView inventoryHolder;
    FloatingActionButton addItemToBagButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_inventory);

        inventoryHolder = findViewById(R.id.inventoryHolder);
        addItemToBagButton = findViewById(R.id.addItemToBagButton);

        Intent intent = getIntent();
        String name = intent.getStringExtra("bag_name");

        inventoryHolder.setText(name);
        addItemToBagButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ItemOverviewActivity.class);
        startActivity(intent);
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
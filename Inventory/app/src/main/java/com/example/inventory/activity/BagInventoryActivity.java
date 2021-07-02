package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inventory.R;

public class BagInventoryActivity extends AppCompatActivity {

    TextView inventoryHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_inventory);

        inventoryHolder = findViewById(R.id.inventoryHolder);

        Intent intent = getIntent();
        String name = intent.getStringExtra("bag_name");

        inventoryHolder.setText(name);
    }
}
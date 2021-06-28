package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.inventory.R;

public class BagInventoryActivity extends AppCompatActivity {

    private EditText insertBagName;
    private EditText insertBagDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_inventory);


    }
}
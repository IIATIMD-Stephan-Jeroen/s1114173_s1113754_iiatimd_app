package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.data.AppDatabase;
import com.example.data.Item;
import com.example.inventory.R;
import com.example.inventory.adapter.BagAdapter;
import com.example.inventory.adapter.ItemAdapter;

public class ItemDetailActivity extends AppCompatActivity {

    ItemAdapter itemAdapter;

    TextView itemName;
    TextView itemCost;
    TextView itemWeight;
    TextView itemProps;
    TextView itemDamage;

    Item item;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        itemAdapter = new ItemAdapter(this.getApplicationContext());

        Intent intent = getIntent();
        itemId = Integer.valueOf(intent.getStringExtra("itemId"));
        item = db.itemDAO().getItemById(itemId);

        itemName = findViewById(R.id.itemDetailName);
        itemCost = findViewById(R.id.itemDetailCost);
        itemWeight = findViewById(R.id.itemDetailWeight);
        itemProps = findViewById(R.id.itemDetailProps);
        itemDamage = findViewById(R.id.itemDetailDamage);

        itemName.setText(item.getName());

        itemCost.setText(item.getCost() + " " + item.getCurrency());

        itemWeight.setText(item.getWeight());


        itemDamage.setText(item.getDamage() + " " + item.getDamage_type());
        String properties = "";

        if(item.getProperty_1() != null && item.getProperty_1() != "") {
            properties += item.getProperty_1();
        }
        if(item.getProperty_2() != null && item.getProperty_2() != "") {
            properties += "  |  ";
            properties += item.getProperty_2();
        }
        if(item.getProperty_3() != null && item.getProperty_3() != "") {
            properties += "  |  ";
            properties += item.getProperty_3();
        }
        if(item.getProperty_4() != null && item.getProperty_4() != "") {
            properties += "  |  ";
            properties += item.getProperty_4();
        }
        itemProps.setText(properties);


        //itemProps.setText(item.getProperty_1() + " | " + item.getProperty_2() + " | " + item.getProperty_3() + " | " + item.getProperty_4() );





    }
}
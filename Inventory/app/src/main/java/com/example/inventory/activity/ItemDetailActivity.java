package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    TextView itemHeaderProps;
    TextView itemProps;

    TextView itemHeaderDamage;
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
        setItemText(item);


    }

    public void setItemText(Item item) {
        itemName = findViewById(R.id.itemDetailName);
        itemCost = findViewById(R.id.itemDetailCost);
        itemWeight = findViewById(R.id.itemDetailWeight);

        itemHeaderProps = findViewById(R.id.itemDetailHeaderProps);
        itemProps = findViewById(R.id.itemDetailProps);

        itemHeaderDamage = findViewById(R.id.itemDetailHeaderDamage);
        itemDamage = findViewById(R.id.itemDetailDamage);

        itemName.setText(item.getName());

        itemCost.setText(item.getCost() + " " + item.getCurrency());

        itemWeight.setText(item.getWeight());

        if(item.getDamage().length() == 0 || item.getDamage().equals("null") || item.getDamage().equals("")) {
            itemDamage.setVisibility(View.GONE);
            itemHeaderDamage.setVisibility(View.GONE);
        } else {
            itemDamage.setVisibility(View.VISIBLE);
            itemHeaderDamage.setVisibility(View.VISIBLE);
            itemDamage.setText(item.getDamage() + " " + item.getDamage_type());
        }

        String properties = "";

        if(item.getProperty_1() != null && !item.getProperty_1().equals("null") && !item.getProperty_1().equals("")) {
            properties += item.getProperty_1();
        }
        if(item.getProperty_2() != null && !item.getProperty_2().equals("null") && !item.getProperty_2().equals("")) {
            properties += "  |  ";
            properties += item.getProperty_2();
        }
        if(item.getProperty_3() != null && !item.getProperty_3().equals("null") && !item.getProperty_3().equals("")) {
            properties += "  |  ";
            properties += item.getProperty_3();
        }
        if(item.getProperty_4() != null && !item.getProperty_4().equals("null") && !item.getProperty_4().equals("")) {
            properties += "  |  ";
            properties += item.getProperty_4();
        }

        if(properties.length() == 0) {
            itemProps.setVisibility(View.GONE);
            itemHeaderProps.setVisibility(View.GONE);
        } else {
            itemProps.setVisibility(View.VISIBLE);
            itemHeaderProps.setVisibility(View.VISIBLE);
            itemProps.setText(properties);
        }
    }
}
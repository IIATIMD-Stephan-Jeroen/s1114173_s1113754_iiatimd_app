package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.data.AppDatabase;
import com.example.data.Bagitem;
import com.example.data.Item;
import com.example.inventory.R;
import com.example.inventory.adapter.ItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ItemAdapter itemAdapter;

    TextView itemName;
    TextView itemCost;
    TextView itemWeight;

    TextView itemHeaderProps;
    TextView itemProps;

    TextView itemHeaderDamage;
    TextView itemDamage;

    FloatingActionButton buttonPlus;
    FloatingActionButton buttonMinus;
    Button saveButton;

    EditText itemAmountInput;

    Item item;
    int itemId;
    int bagId;
    private Bagitem bagitem;
    private AppDatabase db;

    int itemAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        db = AppDatabase.getInstance(this.getApplicationContext());
        Intent intent = getIntent();
        itemId = Integer.valueOf(intent.getStringExtra("itemId"));
        bagId = Integer.valueOf(intent.getStringExtra("bag_id"));

        itemAdapter = new ItemAdapter(this.getApplicationContext(), String.valueOf(bagId));

        try{
            bagitem = db.bagitemDAO().getBagItemById(itemId,bagId);
            itemAmount = bagitem.getAmount();
        }catch(Exception e){
            //
        }

        initButtons();


        item = db.itemDAO().getItemById(itemId);
        setItemText(item);


    }

    @Override
    public void onClick(View v) {
        Log.d("test","BUTTON PLUS PRESSED GLOBAL ONCLICk");

    }


    public void initButtons() {
        buttonPlus = findViewById(R.id.plusItemToInventory);
        buttonMinus = findViewById(R.id.minusItemToInventory);
        saveButton = findViewById(R.id.saveItemToInventory);
        itemAmountInput = findViewById(R.id.inputItemToInventory);


        itemAmountInput.setText(String.valueOf(itemAmount));

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemAmount += 1;
                itemAmountInput.setText(String.valueOf(itemAmount));
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemAmount <= 0){
                    itemAmount = 0;
                    itemAmountInput.setText(String.valueOf(itemAmount));
                }else{
                    itemAmount -= 1;
                    itemAmountInput.setText(String.valueOf(itemAmount));
                }

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemAmount == 0) {
                    try{
                        db.bagitemDAO().delete(bagitem);
                    }catch (Exception e) {}
                } else {
                    Bagitem nBagitem = new Bagitem(itemId, itemAmount, bagId);
                    db.bagitemDAO().insertBagItem(nBagitem);
                }
                finish();
            }
        });

        itemAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    itemAmount = Integer.valueOf(String.valueOf(itemAmountInput.getText()));
                } catch(Exception e) {
                    itemAmount = 0;
                }


            }
        });
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
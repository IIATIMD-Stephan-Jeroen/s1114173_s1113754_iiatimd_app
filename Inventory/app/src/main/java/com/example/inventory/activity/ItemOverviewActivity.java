package com.example.inventory.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.data.AppDatabase;
import com.example.data.Item;
import com.example.inventory.R;
import com.example.inventory.adapter.BagitemAdapter;
import com.example.inventory.adapter.ItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ItemOverviewActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView itemRecyclerView;
    private ItemAdapter itemAdapter;
    private BagitemAdapter bagitemAdapter;
    private List<Item> items;
    private Context mContext;

    private Switch toggleCommunityItemsSwitch;
    private FloatingActionButton addNewCommunityItemButton;
    private int bagId;

    private boolean toggled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);

        toggleCommunityItemsSwitch = findViewById(R.id.toggleCommunityItems);
        addNewCommunityItemButton = findViewById(R.id.addCommunityItemButton);

        mContext = this.getApplicationContext();
        initSwitchBehaviour();

        addNewCommunityItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nIntent = new Intent(mContext, AddCommunityItemActivity.class);
                startActivityWithCallbackToCommunity.launch(nIntent);
            }
        });

        Intent intent = getIntent();
        bagId = Integer.valueOf(intent.getStringExtra("bag_id"));
        this.itemAdapter = new ItemAdapter(mContext, String.valueOf(bagId));
        this.bagitemAdapter = new BagitemAdapter(mContext, bagId);

        items = getAllItems(toggled);
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

    ActivityResultLauncher<Intent> startActivityWithCallbackToCommunity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    items = getAllItems(true);
                    itemAdapter.setItems(items);
                }
            }
    );

    private void initSwitchBehaviour(){
        toggleCommunityItemsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toggled = !toggled;
                if(toggled && isNetworkConnected()) {
                    addNewCommunityItemButton.setVisibility(View.VISIBLE);
                    items = getAllItems(toggled);
                    itemAdapter.setItems(items);
                } else if(!isNetworkConnected()){
                    toggled = false;
                    toggleCommunityItemsSwitch.setChecked(false);
                    addNewCommunityItemButton.setVisibility(View.GONE);

                    new AlertDialog.Builder(ItemOverviewActivity.this)
                            .setTitle("No internet connection")
                            .setMessage("You need to be connected to the internet to use community items")
                            .setPositiveButton("OK",null)
                            .setIcon(android.R.drawable.ic_delete)
                            .show();
                } else{
                    addNewCommunityItemButton.setVisibility(View.GONE);
                    items = getAllItems(toggled);
                    itemAdapter.setItems(items);
                }

            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
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
        itemAdapter.setItems(getAllItems(toggled));
    }



    private void initRecyclerView(){

        itemRecyclerView = findViewById(R.id.itemRecyclerView);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerView.hasFixedSize();

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        itemRecyclerView.addItemDecoration(dividerItemDecoration);


        itemRecyclerView.setAdapter(itemAdapter);
    }

    public List<Item> getAllItems(boolean showCommunityItems) {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        if(showCommunityItems){
            return db.itemDAO().getAllItems();
        }else{
            return db.itemDAO().getAllItemConditionally(false);
        }


    }

    @Override
    public void onClick(View view) {

    }
}
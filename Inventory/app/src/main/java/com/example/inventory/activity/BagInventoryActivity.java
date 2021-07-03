package com.example.inventory.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class BagInventoryActivity extends AppCompatActivity implements View.OnClickListener {

    TextView inventoryHolder;
    FloatingActionButton addItemToBagButton;
    private RecyclerView bagItemRecyclerView;
    private BagitemAdapter bagitemAdapter;
    private String bagId;
    private String bagName;
    private List<Bagitem> items;
    private Boolean onCreateCalled = false;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onCreateCalled = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_inventory);
        Intent intent = getIntent();
        this.bagName = intent.getStringExtra("bag_name");
        this.bagId = intent.getStringExtra("bag_id");

        inventoryHolder = findViewById(R.id.inventoryHolder);
        addItemToBagButton = findViewById(R.id.addItemToBagButton);
        this.bagitemAdapter = new BagitemAdapter(this.getApplicationContext(), Integer.valueOf(bagId));

        mContext = this.getApplicationContext();


        items = getAllItems();
        bagitemAdapter.setItems(items);
        initRecyclerView();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                AppDatabase db = AppDatabase.getInstance(mContext);
                db.bagitemDAO().delete(bagitemAdapter.GetBagItemAt(viewHolder.getAdapterPosition()));
                Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(bagItemRecyclerView);

        inventoryHolder.setText(bagName);
        addItemToBagButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ItemOverviewActivity.class);
        intent.putExtra("bag_id", bagId);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        bagitemAdapter.setItems(getAllItems());
    }


    private void initRecyclerView(){

        bagItemRecyclerView = findViewById(R.id.bagItemRecyclerView);
        bagItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bagItemRecyclerView.hasFixedSize();
        


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
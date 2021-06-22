package com.example.inventory.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.adapter.BagAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView bagRecyclerView;
    private RecyclerView.Adapter bagRecyclerViewAdapter;
    private RecyclerView.LayoutManager bagLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bagRecyclerView = findViewById(R.id.bagRecyclerView);
        bagLayoutManager = new LinearLayoutManager(this);
        bagRecyclerView.setLayoutManager(bagLayoutManager);
        bagRecyclerView.hasFixedSize();

        String[] names = new String[200];
        String[] descriptions = new String[200];

        for(int i = 0; i < 200; i++){
            names[i] = "NAME " + i;
            descriptions[i] = "DESCRIPTION " + i;
        }

        bagRecyclerViewAdapter = new BagAdapter(names, descriptions);
        bagRecyclerView.setAdapter(bagRecyclerViewAdapter);
    }


    // code for the top bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inventory_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
                // go to profile page
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
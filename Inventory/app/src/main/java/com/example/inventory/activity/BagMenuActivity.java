package com.example.inventory.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.data.AppDatabase;
import com.example.data.Bag;
import com.example.data.thread.GetBagTask;
import com.example.inventory.R;
import com.example.inventory.adapter.BagAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BagMenuActivity extends AppCompatActivity implements View.OnClickListener{

    public List<Bag> bagList;

    private BagAdapter bagAdapter;
    private RecyclerView bagRecyclerView;
    private FloatingActionButton addNewBagButton;

    private RecyclerView.LayoutManager bagLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_menu);

        addNewBagButton = findViewById(R.id.addNewBagButton);
        addNewBagButton.setOnClickListener(this);

        initRecyclerView();
        loadBagList();

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
                startActivity(new Intent(BagMenuActivity.this, ProfileActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(BagMenuActivity.this, AddBagActivity.class));
    }

    private void initRecyclerView(){
        Log.d("Debug", "Initiated Recyclerview");

        bagRecyclerView = findViewById(R.id.bagRecyclerView);
        bagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bagRecyclerView.hasFixedSize();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        bagRecyclerView.addItemDecoration(dividerItemDecoration);

        bagAdapter = new BagAdapter(this);

        bagRecyclerView.setAdapter(bagAdapter);
    }

    private void loadBagList(){
        Log.d("Debug", "Starting Data Acquiring");
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());

//        AsyncTask.execute(new GetBagTask(db));
//        Log.d("Debug", "Async Task");

        bagList = db.bagDAO().getAllBags();
        Log.d("Debug", "Data Acquired");
        bagAdapter.setBagList(bagList);
    }
}
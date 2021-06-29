package com.example.inventory.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.data.AppDatabase;
import com.example.data.Bag;
import com.example.data.thread.GetBagTask;
import com.example.data.thread.InsertBagTask;
import com.example.inventory.R;
import com.example.inventory.adapter.BagAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.http.GET;

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

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        // Bag adapter takes information from a bag and places it on a title card.
//        bagRecyclerViewAdapter = new BagAdapter(bags);
//        bagRecyclerView.setAdapter(bagRecyclerViewAdapter);


        // Different threads must be used to do Database operations.
//        new Thread(new InsertBagTask(db, bags[0])).start();
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://iiatimd-stephan-jeroen.herokuapp.com/api/items",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++) {
                            try{
                                JSONObject objectInArray = response.getJSONObject(i);
                                Log.d("Rest response", objectInArray.toString());
                            } catch (JSONException e) {
                                Log.e("Rest error", e.toString());
                            }


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest error", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);

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
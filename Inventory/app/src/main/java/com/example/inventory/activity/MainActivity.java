package com.example.inventory.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.data.AppDatabase;
import com.example.data.Bag;
import com.example.data.Item;
import com.example.inventory.R;
import com.example.inventory.adapter.BagAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BagAdapter.OnBagListener{

    private static final String TAG = "MainActivity";

    private BagAdapter bagAdapter;
    private RecyclerView bagRecyclerView;
    private FloatingActionButton addNewBagButton;
    private Context globalContext;

    AppDatabase db;

    public boolean needToRefresh = false;

    public void setNeedToRefresh(boolean needToRefresh) {
        this.needToRefresh = needToRefresh;
    }

    private RecyclerView.LayoutManager bagLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalContext = this.getApplicationContext();
        db = AppDatabase.getInstance(globalContext);
        bagAdapter = new BagAdapter(globalContext, this);
//        bagAdapter.setBagList(db.bagDAO().getAllBags());
        List<Bag> bagList = db.bagDAO().getAllBags();
        bagAdapter.setBagList(bagList);

        String token = db.userDAO().getAllUsers().get(0).getCookie();
        Log.d(TAG, "onCreate: " + token);

        setContentView(R.layout.activity_main);

        addNewBagButton = findViewById(R.id.addNewBagButton);
        addNewBagButton.setOnClickListener(this);

        initRecyclerView();

        //fill db with items
        ItemDatabaseThread thread = new ItemDatabaseThread(globalContext);
        thread.start();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                db.bagDAO().delete(bagAdapter.GetBagAt(viewHolder.getAdapterPosition()));
                Toast.makeText(globalContext, "Bag Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(bagRecyclerView);
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

    public void addNewItem(int id, String name, String cost, String currency, String type, String weight, String damage, String damage_type, String property_1, String property_2, String property_3, String property_4) {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        Item item = new Item();
        item.id = id;
        item.name = name;
        item.cost = cost;
        item.currency = currency;
        item.type = type;
        item.weight = weight;
        item.damage = damage;
        item.damage_type = damage_type;
        item.property_1 = property_1;
        item.property_2 = property_2;
        item.property_3 = property_3;
        item.property_4 = property_4;
        db.itemDAO().insertItem(item);
    }

    public List<Item> getAllItems() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        return db.itemDAO().getAllItems();
    }

    //super ugly over-complicated code to update recycler view after add activity is closed
    ActivityResultLauncher<Intent> startActivityWithCallback = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    AppDatabase db = AppDatabase.getInstance(globalContext);
                    bagAdapter.setBagList(db.bagDAO().getAllBags());
                }
            }
    );
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AddBagActivity.class);
        startActivityWithCallback.launch(intent);
    }

    private void initRecyclerView(){
        Log.d("Debug", "Initiated Recyclerview");

        bagRecyclerView = findViewById(R.id.bagRecyclerView);
        bagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bagRecyclerView.hasFixedSize();

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        bagRecyclerView.addItemDecoration(dividerItemDecoration);


        bagRecyclerView.setAdapter(bagAdapter);
    }

    // Stuff happening when you click on a bag item
    @Override
    public void onBagClick(int position) {
        Log.d(TAG, "onBagClick:  clicked");

        Intent intent = new Intent(this, BagInventoryActivity.class);
        // send bag name to new activity
        intent.putExtra("bag_name", db.bagDAO().getAllBags().get(position).getName());
        startActivity(intent);
    }

    class ItemDatabaseThread extends Thread {
        Context context;
        ItemDatabaseThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            RequestQueue requestQueue= Volley.newRequestQueue(this.context);

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
                                    int itemId = objectInArray.getInt("id");
                                    String itemName = objectInArray.getString("name");
                                    String itemCost = objectInArray.getString("cost");
                                    String itemCurrency = objectInArray.getString("currency");
                                    String itemType = objectInArray.getString("type");
                                    String itemWeight = objectInArray.getString("weight");

                                    try {
                                        JSONObject relationInfo = objectInArray.getJSONObject("relationInfo");
                                        String damage = relationInfo.getString("damage");
                                        String damage_type = relationInfo.getString("damage_type");
                                        String property_1 = relationInfo.getString("property_1");
                                        String property_2 = relationInfo.getString("property_2");
                                        String property_3 = relationInfo.getString("property_3");
                                        String property_4 = relationInfo.getString("property_4");
                                        addNewItem(itemId, itemName, itemCost, itemCurrency, itemType, itemWeight, damage, damage_type, property_1, property_2, property_3, property_4);

                                    }catch (JSONException e) {
                                        addNewItem(itemId, itemName, itemCost, itemCurrency, itemType, itemWeight, "", "","","","","");
                                    }



                                } catch (JSONException e) {
                                    Log.e("Rest error", e.toString());
                                }


                            }
                            //when done log (testing)

                            //get all items from db
                            List<Item> items = getAllItems();

                            // log all items
                            for(Item item : items) {
                                String itemInfo = "Item info: " + item.id + " " + item.name + " " + item.cost + " " + item.currency + " " + item.type + " " + item.weight + " " + item.damage + " " + item.damage_type + " " + item.property_1 + " " + item.property_2 + " " +item.property_3 + " " + item.property_4;
//                                Log.d("Rest response", itemInfo);
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
    }

}
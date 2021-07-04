package com.example.inventory.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.data.AppDatabase;
import com.example.data.Item;
import com.example.inventory.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AddCommunityItemActivity extends AppCompatActivity {

    private Spinner currencySpinner;
    private Spinner damageSpinner;
    private Spinner damageTypeSpinner;
    private Spinner propOneSpinner;
    private Spinner propTwoSpinner;
    private Spinner propThreeSpinner;
    private Spinner propFourSpinner;
    private FloatingActionButton saveCommunityItemButton;

    private EditText itemName;
    private EditText itemCost;
    private EditText itemWeight;


    public String nItemName;
    public String nItemCost;
    public String nItemCurrency;
    public String nItemWeight;
    public String nItemDamage;
    public String nItemDamageType;
    public String nItemProp1;
    public String nItemProp2;
    public String nItemProp3;
    public String nItemProp4;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_community_item);
        mContext = this.getApplicationContext();
        initSpinners();
        initSaveButtonBehaviour();

        itemName = findViewById(R.id.editTextCommunityItemName);
        itemCost = findViewById(R.id.editTextCommunityItemCost);
        itemWeight = findViewById(R.id.editTextCommunityItemWeight);
    }


    private void initSpinners() {
        currencySpinner = findViewById(R.id.currencySpinner);
        damageSpinner = findViewById(R.id.damageSpinner);
        damageTypeSpinner = findViewById(R.id.damageTypeSpinner);
        propOneSpinner = findViewById(R.id.propOneSpinner);
        propTwoSpinner = findViewById(R.id.propTwoSpinner);
        propThreeSpinner = findViewById(R.id.propThreeSpinner);
        propFourSpinner = findViewById(R.id.propFourSpinner);

        String[] currencies = new String[]{"copper","silver","gold","electrum"};
        currencySpinner.setAdapter(createArrayAdapter(currencies));

        String[] damageDies = new String[]{"None","1d4","1d6","1d8","1d10","1d12","1d20","1d100","2d4","2d6","2d8","2d10","2d12","2d20","2d100"};
        damageSpinner.setAdapter(createArrayAdapter(damageDies));

        String[] damageTypes = new String[]{"None","acid","bludgeoning","cold","fire","force","lightning","nectrotic","piercing","poison","psychic","radiant","slashing","thunder"};
        damageTypeSpinner.setAdapter(createArrayAdapter(damageTypes));

        String[] allProps = new String[]{"None","Ammunition","Finesse","Heavy","Light","Loading","Range","Reach","Special","Thrown","Two-handed","versatile","Improvised","Silvered"};
        ArrayAdapter<String> properties = createArrayAdapter(allProps);
        propOneSpinner.setAdapter(properties);
        propTwoSpinner.setAdapter(properties);
        propThreeSpinner.setAdapter(properties);
        propFourSpinner.setAdapter(properties);
    }

    public void initSaveButtonBehaviour(){
        saveCommunityItemButton = findViewById(R.id.saveCommunityItemButton);


        saveCommunityItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemName.getText().toString().equals("") || itemCost.getText().toString().equals("") || itemWeight.getText().toString().equals("")){
                    showAlertNotComplete();
                }else {
                    nItemName = itemName.getText().toString();
                    nItemCost = itemCost.getText().toString();
                    nItemCurrency = currencySpinner.getSelectedItem().toString();
                    nItemWeight = itemWeight.getText().toString();
                    nItemDamage = (damageSpinner.getSelectedItem().toString());
                    nItemDamageType = (damageTypeSpinner.getSelectedItem().toString());
                    nItemProp1 = (propOneSpinner.getSelectedItem().toString());
                    nItemProp2 = (propTwoSpinner.getSelectedItem().toString());
                    nItemProp3 = (propThreeSpinner.getSelectedItem().toString());
                    nItemProp4 = (propFourSpinner.getSelectedItem().toString());

                    sendItem();
                    //Log.d("test", nItemName + nItemCost + nItemCurrency + nItemWeight + nItemDamage + nItemDamageType+ nItemProp1 + nItemProp2 + nItemProp3 +nItemProp4 );

                }
            }
        });
    }

    public void sendItem(){
        new AlertDialog.Builder(this)
                .setTitle("About to be awesome")
                .setMessage("You are about to send this item to the rest of the community! Are you sure?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                        String URL = "https://iiatimd-stephan-jeroen.herokuapp.com/api/item/insertItem/"+nItemName+"/"+nItemCost+"/"+nItemCurrency+"/"+nItemWeight+"/"+1+"/"+nItemDamage+"/"+nItemDamageType+"/"+nItemProp1+"/"+nItemProp2+"/"+nItemProp3+"/"+nItemProp4;
                        StringRequest postRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("test",response);
                                setItems();
                                sendNotification();
                                finish();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error){
                                Log.d("test", error.getMessage());
                            }
                        });

                        requestQueue.add(postRequest);
                    }
                })
                .setNegativeButton("NO", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void showAlertNotComplete() {
        new AlertDialog.Builder(this)
                .setTitle("Required fields")
                .setMessage("You need to fill in all required fields ya goof! ")
                .setPositiveButton("OK",null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void sendNotification() {
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "APP")
                .setSmallIcon(android.R.drawable.ic_menu_myplaces)
                .setContentTitle("Hey You")
                .setContentText("Thanks for sharing an item!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(123123, builder.build());

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_title);
            String description = "JUP";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("APP", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    public void setItems(){
        RequestQueue requestQueue= Volley.newRequestQueue(mContext);

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
                                boolean communityItem = objectInArray.getBoolean("communityItem");

                                try {
                                    JSONObject relationInfo = objectInArray.getJSONObject("relationInfo");
                                    String damage = relationInfo.getString("damage");
                                    String damage_type = relationInfo.getString("damage_type");
                                    String property_1 = relationInfo.getString("property_1");
                                    String property_2 = relationInfo.getString("property_2");
                                    String property_3 = relationInfo.getString("property_3");
                                    String property_4 = relationInfo.getString("property_4");

                                     addNewItem(itemId, itemName, itemCost, itemCurrency, itemType, itemWeight, communityItem, damage, damage_type, property_1, property_2, property_3, property_4);



                                }catch (JSONException e) {
                                    addNewItem(itemId, itemName, itemCost, itemCurrency, itemType, itemWeight, communityItem, "", "","","","","");
                                }



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

    public List<Item> getAllItems() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        return db.itemDAO().getAllItemConditionally(true);
    }

    public void addNewItem(int id, String name, String cost, String currency, String type, String weight, boolean communityItem, String damage, String damage_type, String property_1, String property_2, String property_3, String property_4) {
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
        item.community_item = communityItem;
        db.itemDAO().insertItem(item);
    }

    private ArrayAdapter<String> createArrayAdapter(String[] list){
        return new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
    }
}
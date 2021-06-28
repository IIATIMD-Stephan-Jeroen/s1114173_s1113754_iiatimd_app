package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.data.AppDatabase;
import com.example.data.Bag;
import com.example.data.thread.GetBagTask;
import com.example.data.thread.InsertBagTask;
import com.example.inventory.R;

public class AddBagActivity extends AppCompatActivity implements View.OnClickListener {

    AppDatabase db;

    public String bagName;
    public String bagDesc;

    private EditText insertBagName;
    private EditText insertBagDesc;
    private Button addBagButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bag);

        insertBagName = findViewById(R.id.insertBagName);
        insertBagDesc = findViewById(R.id.insertBagDesc);
        addBagButton = findViewById(R.id.addBagButton);

        addBagButton.setOnClickListener(this);

        db = AppDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        // Check if Inputs are empty
        if(!isEmpty(insertBagName) && !isEmpty(insertBagDesc)){
            // Get Values from EditTexts
            bagName = insertBagName.getText().toString();
            bagDesc = insertBagDesc.getText().toString();

            //Put values in a Bag
            new Thread(new InsertBagTask(db, new Bag(bagName, bagDesc))).start();

            // go back to bags screen
            startActivity(new Intent(AddBagActivity.this, MainActivity.class));
        }
        else {
            Toast.makeText(this, "Inputs cannot be empty", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length() <= 0;
    }
}
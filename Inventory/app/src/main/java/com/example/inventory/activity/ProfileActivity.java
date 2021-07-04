package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.model.User;
import com.example.api.service.UserClient;
import com.example.inventory.R;
import com.example.inventory.manager.PrefManager;

import org.w3c.dom.Text;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static String token;

    PrefManager prefManager;

    private static final String TAG = "ProfileActivity";

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://iiatimd-stephan-jeroen.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    TextView profileName;
    TextView profileToken;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileName = findViewById(R.id.profileName);
        profileToken = findViewById(R.id.profileToken);
        logoutButton = findViewById(R.id.logoutButton);

        prefManager = new PrefManager(this.getApplicationContext());

        setUserText();
        logoutButton.setOnClickListener(this);
    }

    private void setUserText(){

        Call<User> call = userClient.getUser();
        Log.d(TAG, "setUserText: " + call.toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.toString());
                }
                else {
                    Log.d(TAG, "onResponse: Fail " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: Fail");
                Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getStuff(){
        userClient.getSecret(token);
    }


    @Override
    public void onClick(View v) {
        prefManager.clearSession();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.api.service.UserClient;
import com.example.data.AppDatabase;
import com.example.inventory.R;
import com.example.inventory.manager.PrefManager;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static String token;
    AppDatabase db;

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

        db = AppDatabase.getInstance(this.getApplicationContext());

        logoutButton = findViewById(R.id.logoutButton);

        prefManager = new PrefManager(this.getApplicationContext());

        logoutButton.setOnClickListener(this);
    }

    private void getUser(String token){
        Call<ResponseBody> call = userClient.getUser(token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: Succes " + response.toString());
                Headers headers = response.headers();
                Log.d(TAG, "onResponse: " + headers);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: Fail");
            }
        });
    }

    @Override
    public void onClick(View v) {
        prefManager.clearSession();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
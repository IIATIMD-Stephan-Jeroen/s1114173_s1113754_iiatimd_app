package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.service.UserClient;
import com.example.inventory.R;

import org.w3c.dom.Text;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://iiatimd-stephan-jeroen.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    TextView profileName;
    TextView profileToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileName = findViewById(R.id.profileName);
        profileToken = findViewById(R.id.profileToken);

        setUserText();
    }

    private void setUserText(){

        Call<ResponseBody> call = userClient.getToken();
        Log.d(TAG, "setUserText: " + call.toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                profileToken.setText(call.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
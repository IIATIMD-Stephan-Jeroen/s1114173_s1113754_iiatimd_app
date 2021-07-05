package com.example.inventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.model.Login;
import com.example.api.model.ApiUser;
import com.example.api.service.UserClient;
import com.example.data.AppDatabase;
import com.example.data.User;
import com.example.inventory.R;
import com.example.inventory.manager.PrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    PrefManager prefManager;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://iiatimd-stephan-jeroen.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    private static final String TAG = "LoginActivity";

    private AppDatabase db;

    private Button loginButton;
    private EditText mailInput;
    private EditText passwordInput;

    private TextView signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = AppDatabase.getInstance(getApplicationContext());
        prefManager = new PrefManager(this.getApplicationContext());

        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        mailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    private static String token;

    private void login(){

        Login login = new Login(mailInput.getText().toString(), passwordInput.getText().toString());
        Call<ApiUser> call = userClient.login(login);
        
        call.enqueue(new Callback<ApiUser>() {
            @Override
            public void onResponse(Call<ApiUser> call, Response<ApiUser> response) {
                if (response.isSuccessful()){
                    prefManager.createLogin();

                    token = response.body().getToken();
                    Log.d(TAG, "onResponse: " + token);

                    User user = new User(response.body().getName(), response.body().getToken());
                    db.userDAO().InsertUser(user);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                login();
                break;

            case R.id.signUpButton:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    protected void onResume(){
        super.onResume();
        // Checking for user session.
        // if user already logged in, take them to mainactivity.
        if(prefManager.isLoggedIn()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.model.Register;
import com.example.api.model.User;
import com.example.api.service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://iiatimd-stephan-jeroen.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    TextView logInText;

    EditText registerName;
    EditText registerEmail;
    EditText registerPassword;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        logInText = findViewById(R.id.loginText);

        registerName = findViewById(R.id.registerName);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);

        registerButton = findViewById(R.id.signUpButton);

        logInText.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }

    public void Register(){
        Register register = new Register(registerName.getText().toString(), registerEmail.getText().toString(), registerPassword.getText().toString());
        Call<User> call = userClient.register(register);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }

                else{
                    Toast.makeText(RegisterActivity.this, "Email is already in use", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginText:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.signUpButton:
                Register();
                break;
        }
    }
}
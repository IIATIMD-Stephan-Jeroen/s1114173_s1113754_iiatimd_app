package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jackrutorial.androidloginrestfulwebserviceexample.remote.UserService;
import com.jackrutorial.androidloginrestfulwebserviceexample.remote.ApiUtils;
import com.jackrutorial.androidloginrestfulwebserviceexample.model.ResObj;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText mailInput;
    private EditText passwordInput;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        mailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        userService = ApiUtils.getUserService();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mailInput.getText().toString();
                String password = passwordInput.getText().toString();
                //validate form
                if(validateLogin(email, password)){
                    //do login
                    doLogin(email, password);
                }
            }
        });
    }

    private boolean validateLogin(String email, String password){
        if(email == null || email.trim().length() == 0){
            Toast.makeText(this, "email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String email,final String password){
        Call call = userService.login(email,password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    ResObj resObj = (ResObj) response.body();
                    if(resObj.getMessage().equals("true")){
                        //login start main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "The email or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
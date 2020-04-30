package com.example.ocenika.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocenika.R;
import com.example.ocenika.model.Login;
import com.example.ocenika.model.Token;
import com.example.ocenika.service.UserService;
import com.example.ocenika.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.ocenika.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    UserService userService = retrofit.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (PreferenceUtils.getToken(this) != null && !PreferenceUtils.getToken(this).equals("")) {
            skip();
        }
        findViewById(R.id.login).setOnClickListener(v -> login());
        findViewById(R.id.login_skip).setOnClickListener(v -> skip());
    }

    public void login() {
        Login login = new Login("admin", "admin");
        Call<Token> call = userService.login(login);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token token = response.body();
                    String tokenKey = token.getToken();
                    if (tokenKey != null && !tokenKey.isEmpty()) {
                        PreferenceUtils.saveToken(tokenKey, LoginActivity.this);
                        Toast.makeText(LoginActivity.this, tokenKey, Toast.LENGTH_SHORT).show();
                        skip();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "login not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void skip() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}

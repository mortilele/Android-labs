package com.example.ocenika.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocenika.R;
import com.example.ocenika.fragment.LoginFragment;
import com.example.ocenika.util.PreferenceUtils;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (PreferenceUtils.getToken(this) != null && !PreferenceUtils.getToken(this).equals("")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commit();
        }

    }




}

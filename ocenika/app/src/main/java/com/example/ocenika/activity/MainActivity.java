package com.example.ocenika.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ocenika.R;
import com.example.ocenika.fragment.ProfessorListFragment;
import com.example.ocenika.fragment.UniversityListFragment;
import com.example.ocenika.util.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, UniversityListFragment.newInstance())
                    .commit();
        }
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch(item.getItemId()) {
                    case R.id.nav_universities:
                        selectedFragment = UniversityListFragment.newInstance();
                        break;
                    case R.id.nav_professors:
                        selectedFragment = ProfessorListFragment.newInstance();
                        break;
                }
                replaceFragment(selectedFragment);
                return true;
            };

    public void replaceFragment(Fragment someFragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, someFragment)
                .addToBackStack("second")
                .commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean loggedIn = PreferenceUtils.getToken(this) != null && !PreferenceUtils.getToken(this).equals("");
        getMenuInflater().inflate(R.menu.main_menu, menu);

        if(loggedIn){
            MenuItem item = menu.findItem(R.id.menu_logout);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.menu_login);
            items.setVisible(false);

        }
        else {
            MenuItem item = menu.findItem(R.id.menu_login);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.menu_logout);
            items.setVisible(false);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_login:
                Intent a = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(a);
                finish();
                return true;
            case R.id.menu_logout:
                PreferenceUtils.saveToken("", this);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return true;
    }
}

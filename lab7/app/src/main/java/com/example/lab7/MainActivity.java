package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch(item.getItemId()) {
                    case R.id.nav_jobs:
                        selectedFragment = MainFragment.newInstance();
                        break;
                    case R.id.nav_favorites:
                        selectedFragment = FavoritesFragment.newInstance();
                        break;
                    case R.id.nav_more:
                        selectedFragment = MoreFragment.newInstance();
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


}

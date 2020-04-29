package com.example.ocenika.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;


import android.os.Bundle;

import com.example.ocenika.R;
import com.example.ocenika.fragment.ProfessorListFragment;
import com.example.ocenika.fragment.UniversityListFragment;
import com.example.ocenika.service.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
//                        break;
//                    case R.id.nav_profile:
//                        selectedFragment = ProfileFragment.newInstance();
//                        break;
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

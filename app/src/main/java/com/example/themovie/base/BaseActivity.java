package com.example.themovie.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class BaseActivity extends AppCompatActivity {
    public void addFragment (Fragment fragment, int viewId) {
        FragmentManager homeFragmentManager = getSupportFragmentManager();
        homeFragmentManager.beginTransaction()
                .add(viewId, fragment)
                .addToBackStack(null)
                .commit();
    }
}

package com.example.themovie.ui.main;

import android.os.Bundle;

import com.example.themovie.R;
import com.example.themovie.base.BaseActivity;
import com.example.themovie.ui.home.HomeFragment;
import com.github.ybq.android.spinkit.SpinKitView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {
    @BindView(R.id.actMain_spinKit)
    SpinKitView spKLoading;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        addFragment(new HomeFragment(), R.id.actMain_flContentScreens);
    }

    public void showLoading() {
        spKLoading.setVisibility(SpinKitView.VISIBLE);
    }

    public void hideLoading() {
        spKLoading.setVisibility(SpinKitView.INVISIBLE);
    }

}

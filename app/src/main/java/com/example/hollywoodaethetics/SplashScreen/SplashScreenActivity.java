package com.example.hollywoodaethetics.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.hollywoodaethetics.HeatherBasedApplications.HeatherBasedActivity;
import com.example.hollywoodaethetics.R;

import butterknife.BindView;

public class SplashScreenActivity extends AppCompatActivity {


    private final int SPLASH_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getWindow().setStatusBarColor(Color.TRANSPARENT );
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, HeatherBasedActivity.class));
                finish();
            }
        }, SPLASH_DURATION);
    }

    @Override
    protected void onDestroy() {
       getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(getResources().getColor(R.color.dark,null));
        super.onDestroy();
    }
}

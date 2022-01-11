package com.example.hollywoodaethetics.HeatherBasedApplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.HomeFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.LegendaryFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.LibraryFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.ProfileFragment;
import com.example.hollywoodaethetics.LogInScreen.LogInActivity;
import com.example.hollywoodaethetics.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class HeatherBasedActivity extends AppCompatActivity {

    SharedPreferences preferences;
    @BindView(R.id.home_bottom_navigation)
    SmoothBottomBar navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heather_based);
        ButterKnife.bind(this);


        preferences = getSharedPreferences("UserLocals", Context.MODE_PRIVATE);
        if (preferences.contains("loginstate")) {
            if (!preferences.getBoolean("loginstate", false)) {
                startActivity(new Intent(this, LogInActivity.class));
                finish();
            }
        } else {
            startActivity(new Intent(this, LogInActivity.class));
            finish();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.heather_container,new HomeFragment()).commit();

        navigationView.setOnItemSelectedListener(navListener);
        navigationView.setActiveItem(1);



    }



    private OnItemSelectedListener navListener = new OnItemSelectedListener() {
        @Override
        public boolean onItemSelect(int i) {

                    switch (i){
                        case 3:
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getSupportFragmentManager().beginTransaction().replace(R.id.heather_container, new ProfileFragment(), "tag1").commit();
                                }
                            },200);
                            break;
                        case 2:
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getSupportFragmentManager().beginTransaction().replace(R.id.heather_container, new LibraryFragment(), "tag1").commit();
                                }
                            },200);
                            break;
                        case 1:
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getSupportFragmentManager().beginTransaction().replace(R.id.heather_container, new HomeFragment(), "tag1").commit();
                                }
                            },200);
                            break;
                        case 0:
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getSupportFragmentManager().beginTransaction().replace(R.id.heather_container, new LegendaryFragment(), "tag1").commit();
                                }
                            },200);
                            break;

                    }


            return false;
        }
    };

    private void threadfragmentload(Fragment fragment){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().replace(R.id.heather_container,fragment,"tag3").commit();
            }
        },20);

    }



//    private SmoothBottomBar. navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//                @Override
//                public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            switch (menuItem.getItemId()){
//                                case  R.id.home_dashboard:
//
//                                    getSupportFragmentManager().beginTransaction().replace(R.id.heather_container,new HomeFragment()).commit();
//                                    break;
//
//                                case R.id.home_profile:
//
//                                    getSupportFragmentManager().beginTransaction().replace(R.id.heather_container,new ProfileFragment()).commit();
//                                    break;
//
//
//                            }
//                        }
//                    }, 10);
//
//                    return true;
//                }
//            };


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}

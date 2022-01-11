package com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.hollywoodaethetics.HeatherBasedApplications.HeatherBasedActivity;
import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.ViewModel.LeaderboardsViewModel;
import com.example.hollywoodaethetics.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderboardsActivity extends AppCompatActivity {

    @BindView(R.id.leader_toolbar)
    Toolbar toolbar;
    @BindView(R.id.leader_tab)
    TabLayout leadertab;
    LeaderContainerFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });

        fragment = new LeaderContainerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category","incline");
        if(getSharedPreferences("UserLocals", Context.MODE_PRIVATE).getString("metric","").equals("metric"))
        bundle.putBoolean("metric",true);
            else bundle.putBoolean("metric",false);
        fragment.setArguments(
                bundle
        );
        getSupportFragmentManager().beginTransaction().replace(R.id.legend_tab_container,fragment,"tabfragment").commit();

       leadertab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition()) {
                   case 0: {
                       getSupportFragmentManager().beginTransaction().remove(fragment);
                       fragment = new LeaderContainerFragment();
                       bundle.putString("category", "incline");
                       fragment.setArguments(bundle);
                       getSupportFragmentManager().beginTransaction().replace(R.id.legend_tab_container, fragment).commit();
                       break;
                   }
                   case 1: {
                       getSupportFragmentManager().beginTransaction().remove(fragment);
                       fragment = new LeaderContainerFragment();
                       bundle.putString("category", "chinups");
                       fragment.setArguments(bundle);
                       getSupportFragmentManager().beginTransaction().replace(R.id.legend_tab_container, fragment).commit();
                       break;
                   }
                   case 2: {
                       getSupportFragmentManager().beginTransaction().remove(fragment);
                       fragment = new LeaderContainerFragment();
                       bundle.putString("category", "deadlift");
                       fragment.setArguments(bundle);
                       getSupportFragmentManager().beginTransaction().replace(R.id.legend_tab_container, fragment).commit();
                       break;
                   }
                   case 3: {
                       getSupportFragmentManager().beginTransaction().remove(fragment);
                       fragment = new LeaderContainerFragment();
                       bundle.putString("category", "press");
                       fragment.setArguments(bundle);
                       getSupportFragmentManager().beginTransaction().replace(R.id.legend_tab_container, fragment).commit();
                       break;
                   }
               }
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
    }
}

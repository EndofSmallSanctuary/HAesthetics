package com.example.hollywoodaethetics.HeatherBasedApplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.ProfileFragment;
import com.example.hollywoodaethetics.R;

import butterknife.BindView;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle bundle = new Bundle();
        bundle.putString("id",getIntent().getStringExtra("id"));
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.profile_acitvity_container,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}

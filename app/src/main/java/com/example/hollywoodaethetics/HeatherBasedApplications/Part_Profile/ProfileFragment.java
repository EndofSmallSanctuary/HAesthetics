package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Data.UserG;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.ViewModel.ProfileViewModel;
import com.example.hollywoodaethetics.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ProfileViewModel viewModel;
    Intent intent;

    @BindView(R.id.profile_container)
    ConstraintLayout profilecontainer;
    @BindView(R.id.profile_loading)
    RelativeLayout loading;
    @BindView(R.id.profile_backbutton)
    ImageView backbutton;

    @BindView(R.id.profile_avatar)
    ImageView avatar;
    @BindView(R.id.profile_view_header)
    ImageView background;
    @BindView(R.id.profile_starteedit)
    ImageView editprofile;
    @BindView(R.id.profile_name)
    TextView profilename;
    @BindView(R.id.profile_physique_header)
    TextView physique;
    @BindView(R.id.profile_split)
    TextView split;
    @BindView(R.id.profile_fullname)
    TextView fullname;
    @BindView(R.id.profile_gender)
    TextView gender;
    @BindView(R.id.profile_height)
    TextView height;
    @BindView(R.id.profile_weight)
    TextView weight;
    @BindView(R.id.profile_incline)
    TextView incline;
    @BindView(R.id.profile_chinups)
    TextView chinups;
    @BindView(R.id.profile_deadlift)
    TextView deadlift;
    @BindView(R.id.profile_press)
    TextView press;



    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       intent = new Intent(getActivity(),ProfileEditActivity.class);
        String id;
       if((getArguments()!=null) && getArguments().containsKey("id")) id = getArguments().getString("id");
       else  id = getActivity().getSharedPreferences("UserLocals",Context.MODE_PRIVATE).getString("id","");

        intent.putExtra("id",id);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        viewModel.setProfileLinked(this,id);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        ButterKnife.bind(this,view);
        if((getArguments()!=null) && getArguments().containsKey("id")){
            editprofile.setVisibility(View.GONE);
            backbutton.setVisibility(View.VISIBLE);
            backbutton.setOnClickListener(view1 -> {
                getActivity().onBackPressed();
            });}
        else {
            editprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }

        viewModel.getProfiledata().observe(getActivity(), new Observer<UserG>() {
            @Override
            public void onChanged(UserG userG) {

                intent.putExtra("avatar",userG.avatar);
                Glide.with(ProfileFragment.this).load(userG.avatar)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(avatar);

                if (!userG.background.equals("")) {
                    if(getActivity().getSharedPreferences("UserLocals",Context.MODE_PRIVATE).getBoolean("backgroundcache",true)) {
                        Glide.with(ProfileFragment.this).load(userG.background)
                             .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(background);
                    } else {
                        Glide.with(ProfileFragment.this).load(userG.background)
                                .signature(new ObjectKey(String.valueOf(System.currentTimeMillis())))
                                .into(background);
                        getActivity().getSharedPreferences("UserLocals",Context.MODE_PRIVATE).edit().putBoolean("backgroundcache",true).apply();
                    }
                }
                intent.putExtra("background",userG.background);

                gender.setText(userG.gender);
                profilename.setText(userG.username);
                split.setText(userG.split);
                fullname.setText(userG.fullname);
                physique.setText(userG.physique);



                height.setText(Integer.toString(userG.height));
                weight.setText(Integer.toString(userG.weight));
                if(userG.metric.equals("metric")){
                    height.append(" cm");
                    weight.append(" kg");
                }
                else {
                    height.setText(height.getText().charAt(0)+"'"+height.getText().charAt(1)+" ft");
                    weight.append(" lbs");
                }

                if(userG.metric.equals("metric")) {
                    incline.setText(Float.toString(userG.incline) + " kg");
                    chinups.setText(Float.toString(userG.chinups)+ " kg");
                    deadlift.setText(Float.toString(userG.deadlift)+ " kg");
                    press.setText(Float.toString(userG.press)+ " kg");
                } else {
                    incline.setText(String.format("%.1f", userG.incline*2.2f) + " lbs");
                    chinups.setText(String.format("%.1f", userG.chinups*2.2f) + " lbs");
                    deadlift.setText(String.format("%.1f", userG.deadlift*2.2f) + " lbs");
                    press.setText(String.format("%.1f", userG.press*2.2f) + " lbs");
                }

                intent.putExtra("gender",userG.gender);
                intent.putExtra("username",userG.username);
                intent.putExtra("metric",userG.metric);
                intent.putExtra("email",userG.email);
                intent.putExtra("fullname",userG.fullname);
                intent.putExtra("physique",userG.physique);
                intent.putExtra("split",userG.split);
                intent.putExtra("incline",Float.parseFloat(incline.getText().toString().split(" ")[0].replace(',','.')));
                intent.putExtra("chinups",Float.parseFloat(chinups.getText().toString().split(" ")[0].replace(',','.')));
                intent.putExtra("deadlift",Float.parseFloat(deadlift.getText().toString().split(" ")[0].replace(',','.')));
                intent.putExtra("press",Float.parseFloat(press.getText().toString().split(" ")[0].replace(',','.')));
                intent.putExtra("height",userG.height);
                intent.putExtra("weight",userG.weight);

                loading.setVisibility(View.GONE);
                profilecontainer.setVisibility(View.VISIBLE);
            }

        });

         return view;
    }

    @Override
    public void onStart() {
              super.onStart();
    }

    @Override
    public void onResume() {

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT );
        viewModel.getProfile();
        super.onResume();
    }



    @Override
    public void onStop() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                         View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.dark,null));
        super.onStop();
    }


    ///Avatar send section

}

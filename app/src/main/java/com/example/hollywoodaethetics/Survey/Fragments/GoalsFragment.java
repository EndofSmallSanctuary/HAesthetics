package com.example.hollywoodaethetics.Survey.Fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.SurveyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalsFragment extends Fragment{

    @BindView(R.id.goals_build_muscle)
    ConstraintLayout build_muscle;
    @BindView(R.id.goals_build_strength)
    ConstraintLayout build_strength;
    @BindView(R.id.goals_lose_fat)
    ConstraintLayout lose_fat;

    @BindView(R.id.goals_build_muscle_check)
    ImageView build_muscle_icon;
    @BindView(R.id.goals_build_strength_check)
    ImageView build_strength_icon;
    @BindView(R.id.goals_lose_fat_check)
    ImageView lose_fat_icon;

    ConstraintLayout[] offers;
    ImageView[] icons;

    int[] goals;


    public GoalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_goals, container, false);
       ButterKnife.bind(this, view);

       offers = new ConstraintLayout[]{build_muscle,build_strength,lose_fat};
       icons = new ImageView[]{build_muscle_icon,build_strength_icon,lose_fat_icon};



        for(int i = 0; i<offers.length; i++){
            setUPonClick(offers[i],icons[i],i);
        }

        goals = new int[]{0,0,0};

        return view;

    }

    private void setUPonClick(ConstraintLayout l, ImageView i, int position){

        l.setOnClickListener(view -> {

            if(i.getVisibility() == View.GONE){
                l.setBackgroundResource(R.drawable.offer_selected);
                i.setVisibility(View.VISIBLE);
                goals[position] = 1;

            } else {
                l.setBackgroundResource(R.drawable.offer_unselected);
                i.setVisibility(View.GONE);
                goals[position] = 0;

            }
        });
    }

    @Override
    public void onDetach() {
        SurveyActivity.goals = goals;
        Log.d("Dogs",Integer.toString(goals[0]));
        super.onDetach();
    }
}

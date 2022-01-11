package com.example.hollywoodaethetics.Survey.Fragments;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.SurveyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FitnessLevelFragment extends Fragment {
    @BindView(R.id.fitness_level_Newbie) ConstraintLayout newbielevel;
    @BindView(R.id.fitness_level_Beginner) ConstraintLayout begginerlevel;
    @BindView(R.id.fitness_level_Advanced) ConstraintLayout advancedlevel;
    @BindView(R.id.fitness_level_Intermediate) ConstraintLayout intermidiatelevel;

    @BindView(R.id.fitness_level_newbie_check) ImageView newbiecheck;
    @BindView(R.id.fitness_level_beginner_check) ImageView begginercheck;
    @BindView(R.id.fitness_level_intermediate_check) ImageView intermediatecheck;
    @BindView(R.id.fitness_level_advanced_check) ImageView advancedcheck;

    ConstraintLayout[] layouts;
    ImageView[] icons;

    int chosenposition;

    public FitnessLevelFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fitness_level,container,false);
        ButterKnife.bind(this,view);
        layouts =  new ConstraintLayout[]{newbielevel,begginerlevel,intermidiatelevel,advancedlevel};
        icons  = new ImageView[]{newbiecheck,begginercheck,intermediatecheck,advancedcheck};

        for (int i=0; i<layouts.length; i++) {
            SetListener(layouts[i],icons[i],i);


        }
        return view;
    }

    private void HideAllBut(){
       for(int i=0; i<layouts.length;i++){
            if (i!=chosenposition){
                layouts[i].setBackgroundResource(R.drawable.offer_unselected);
                icons[i].setVisibility(View.GONE);
            }
       }
    }

    private void SetListener(ConstraintLayout layout, ImageView imageView, int position){
        layout.setOnClickListener(view -> {
            layout.setBackgroundResource(R.drawable.offer_selected);
            imageView.setVisibility(View.VISIBLE);
            chosenposition = position;
            HideAllBut();
        }
        );
    }

    @Override
    public void onDetach() {
        SurveyActivity.fitnesslevel = chosenposition;
        Log.d("Dogs",Integer.toString(chosenposition));
        super.onDetach();
    }

}

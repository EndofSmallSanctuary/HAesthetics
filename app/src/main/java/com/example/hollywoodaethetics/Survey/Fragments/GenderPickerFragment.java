package com.example.hollywoodaethetics.Survey.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.Repository.SurveyRepository;
import com.example.hollywoodaethetics.Survey.SurveyActivity;

public class GenderPickerFragment extends Fragment {

    ConstraintLayout offermale;
    ConstraintLayout offerfemale;

    public GenderPickerFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_gender_picker,container,false);

       offermale = view.findViewById(R.id.genderpicker_male);
       offerfemale = view.findViewById(R.id.genderpicker_female);



       offermale.setBackgroundResource(R.drawable.offer_selected);
       offerfemale.setBackgroundResource(R.drawable.offer_unselected);
       SurveyActivity.gender = "Male";

       offerfemale.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                offerfemale.setBackgroundResource(R.drawable.offer_selected);
                offermale.setBackgroundResource(R.drawable.offer_unselected);
               SurveyActivity.gender = "Female";
           }
       });

        offermale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offermale.setBackgroundResource(R.drawable.offer_selected);
                offerfemale.setBackgroundResource(R.drawable.offer_unselected);
                SurveyActivity.gender = "Male";
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

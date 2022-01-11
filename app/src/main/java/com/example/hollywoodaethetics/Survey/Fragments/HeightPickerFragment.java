package com.example.hollywoodaethetics.Survey.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.SurveyActivity;

public class HeightPickerFragment extends Fragment {
    private TextView cmpicker, inpicker;
    private NumberPicker numpicker;

    public HeightPickerFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_height_picker,container,false);

        cmpicker = view.findViewById(R.id.height_picker_cm);
        inpicker = view.findViewById(R.id.height_picker_in);
        numpicker = view.findViewById(R.id.height_picker);

        numpicker.setMinValue(140);
        numpicker.setMaxValue(220);
        numpicker.setValue(175);
        numpicker.computeScroll();
        SurveyActivity.metric = "metric";
        SurveyActivity.height = 175;

        cmpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inpicker.setTextColor(getResources().getColor(R.color.white,null));
                inpicker.setBackgroundResource(R.drawable.switch_unselected);

                cmpicker.setTextColor(getResources().getColor(R.color.main,null));
                cmpicker.setBackgroundResource(R.drawable.switch_selected);

                numpicker.setMinValue(140);
                numpicker.setMaxValue(220);
                numpicker.setValue(175);
                numpicker.computeScroll();
            }
        });

        inpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cmpicker.setTextColor(getResources().getColor(R.color.white,null));
                cmpicker.setBackgroundResource(R.drawable.switch_unselected);

                inpicker.setTextColor(getResources().getColor(R.color.main,null));
                inpicker.setBackgroundResource(R.drawable.switch_selected);

                numpicker.setMinValue(49);
                numpicker.setMaxValue(67);
                numpicker.setValue(59);
                numpicker.computeScroll();


            }
        });

        return  view;
    }

    @Override
    public void onDetach() {
        if(numpicker.getValue()<70){
            SurveyActivity.metric = "imperial";
        }
        else {
            SurveyActivity.metric = "metric";
        }
        SurveyActivity.height = (numpicker.getValue());
        super.onDetach();
    }
}

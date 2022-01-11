package com.example.hollywoodaethetics.Survey.Fragments;

import android.os.Bundle;
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

public class WeightPickerFragment extends Fragment {

    private TextView kgpicker,lbspicker;
    private NumberPicker numpicker;

    public WeightPickerFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_weight_picker,container,false);

       kgpicker = view.findViewById(R.id.weight_picker_kg);
       lbspicker = view.findViewById(R.id.weight_picker_lbs);
       numpicker = view.findViewById(R.id.weight_picker);




        if (SurveyActivity.metric == "metric"){

            numpicker.setMinValue(50);
            numpicker.setMaxValue(140);
            numpicker.setValue(75);
            numpicker.computeScroll();

        } else {

            kgpicker.setBackgroundResource(R.drawable.switch_unselected);
            lbspicker.setBackgroundResource(R.drawable.switch_selected);
            kgpicker.setTextColor(getResources().getColor(R.color.white,null));
            lbspicker.setTextColor(getResources().getColor(R.color.main,null));

            numpicker.setMinValue(140);
            numpicker.setMaxValue(308);
            numpicker.setValue(165);
            numpicker.computeScroll();
        }


        return view;

    }

    @Override
    public void onDetach() {
        SurveyActivity.weight = numpicker.getValue();
        super.onDetach();
    }
}

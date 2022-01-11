package com.example.hollywoodaethetics.Survey.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.SurveyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerformanceFragment extends Fragment {
    @BindView(R.id.performance_metric)
    TextView metrictext;
    @BindView(R.id.performance_max_incline_input)
    EditText incline;
    @BindView(R.id.performance_max_chinups_input)
    EditText chinups;
    @BindView(R.id.performance_max_deadlift_input)
    EditText deadlift;
    @BindView(R.id.performance_max_press_input)
    EditText shouldepress;

    EditText[] inputs;


    public PerformanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_performance, container, false);
      ButterKnife.bind(this,view);

      if (SurveyActivity.metric == "imperial"){
          metrictext.setText(getResources().getText(R.string.lbs));
      }
      else metrictext.setText(getResources().getText(R.string.kg));

      inputs =  new EditText[]{incline,chinups,deadlift,shouldepress};
        for (int i =0; i<inputs.length; i++){
            setUpOnEdit(inputs[i],i);
        }

      return view;

    }

    private void setUpOnEdit(EditText e, int position){
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(e.getText().toString().length()!=0)
                SurveyActivity.performance[position] = Float.parseFloat(e.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

package com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.WorkoutDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Data.exInterpretationResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.ViewModel.exInterpretationViewModel;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.WorkoutDetails.Adapter.WorkoutDetailsAdapter;
import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.WorkoutNotes.WorkoutNoteEditActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutDetailsActivity extends AppCompatActivity {

    exInterpretationViewModel viewModel;
    WorkoutDetailsAdapter adapter;
    Intent intent;

    @BindView(R.id.workout_details_to_notes)
    Button toNotes;
    @BindView(R.id.workout_details_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.workout_details_backbutton)
    ImageView backbutton;
    @BindView(R.id.workout_details_dailyprev)
    ImageView prev;
    @BindView(R.id.workout_details_date)
    TextView date;
    @BindView(R.id.workout_details_name)
    TextView name;
    @BindView(R.id.workout_details_split)
    TextView split;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);
        ButterKnife.bind(this);
        viewModel = new ViewModelProvider(this).get(exInterpretationViewModel.class);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("prev")).into(prev);
        name.setText(intent.getStringExtra("name"));
        date.setText(intent.getStringExtra("date"));
        split.setText(intent.getStringExtra("split"));
        backbutton.setOnClickListener(view -> {
            onBackPressed();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter = new WorkoutDetailsAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getYoutubeDetailsResponce(intent.getStringExtra("exids")).observe(this, new Observer<List<exInterpretationResponce>>() {
            @Override
            public void onChanged(List<exInterpretationResponce> exInterpretationResponces) {
                adapter.setExInterpretationResponces(WorkoutDetailsActivity.this,exInterpretationResponces,intent.getStringExtra("reps"));
                adapter.notifyDataSetChanged();
            }
        });


        if(intent.getBooleanExtra("local?",false)) toNotes.setVisibility(View.INVISIBLE);
            else toNotes.setOnClickListener(view -> {
            intent = new Intent(this, WorkoutNoteEditActivity.class);
            intent.putExtra("date",date.getText());
            intent.putExtra("name",name.getText());
            intent.putExtra("split",split.getText());
            startActivity(intent);

            });




    }
}

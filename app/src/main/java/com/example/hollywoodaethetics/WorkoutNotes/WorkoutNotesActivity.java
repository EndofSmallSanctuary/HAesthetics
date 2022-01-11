package com.example.hollywoodaethetics.WorkoutNotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;


import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.WorkoutNotes.Adapters.WorkoutNotesAdapter;
import com.example.hollywoodaethetics.WorkoutNotes.ViewModel.WorkoutNoteViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutNotesActivity extends AppCompatActivity {

    WorkoutNoteViewModel viewModel;
    WorkoutNotesAdapter adapter;
    @BindView(R.id.notes_toolbar)
    Toolbar toolbar;
    @BindView(R.id.notes_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_notes);
        ButterKnife.bind(this);
        viewModel = new ViewModelProvider(this).get(WorkoutNoteViewModel.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });


    }

    @Override
    protected void onResume() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter = new WorkoutNotesAdapter(this,viewModel.getWorkoutNotes(this));
        recyclerView.setAdapter(adapter);

        super.onResume();
    }
}

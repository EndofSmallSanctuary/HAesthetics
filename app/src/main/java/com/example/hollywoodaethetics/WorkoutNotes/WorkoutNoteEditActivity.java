package com.example.hollywoodaethetics.WorkoutNotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.WorkoutNotes.Data.WorkoutNote;
import com.example.hollywoodaethetics.WorkoutNotes.ViewModel.WorkoutNoteViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutNoteEditActivity extends AppCompatActivity {

    WorkoutNoteViewModel viewModel;
    Boolean editing = false;
    WorkoutNote existNote;

    @BindView(R.id.edit_notes_backbutton)
    ImageView backbutton;
    @BindView(R.id.edit_note_date)
    TextView date;
    @BindView(R.id.edit_note_name)
    TextView name;
    @BindView(R.id.edit_note_split)
    TextView split;
    @BindView(R.id.edit_note_content)
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_note_edit);
        ButterKnife.bind(this);
        viewModel = new ViewModelProvider(this).get(WorkoutNoteViewModel.class);
        backbutton.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        date.setText(intent.getStringExtra("date"));
        name.setText(intent.getStringExtra("name"));
        split.setText(intent.getStringExtra("split"));

       existNote = viewModel.findworkoutNote(this,intent.getStringExtra("date"));
        if(existNote!=null){
            content.setText(existNote.content);
            editing = true;
        }
//        if(!intent.getStringExtra("content").isEmpty()) content.setText(intent.getStringExtra("content"));
    }

    @Override
    public void onPause() {
        if((content.getText().length()!=0) && (!editing))
            viewModel.saveWorkoutNote(this,date.getText().toString(),name.getText().toString(),split.getText().toString(),content.getText().toString());
        if((content.getText().length()!=0) && (editing))
            viewModel.updateNote(this,existNote.getId(),content.getText().toString());
        super.onPause();
    }
}

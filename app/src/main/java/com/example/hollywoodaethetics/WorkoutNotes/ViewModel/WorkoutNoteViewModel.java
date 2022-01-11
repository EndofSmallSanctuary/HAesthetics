package com.example.hollywoodaethetics.WorkoutNotes.ViewModel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hollywoodaethetics.WorkoutNotes.Data.WorkoutNote;
import com.example.hollywoodaethetics.WorkoutNotes.Repository.WorkoutNoteLocalRepository;

import java.util.List;

public class WorkoutNoteViewModel extends AndroidViewModel {
    private WorkoutNote workoutNote;
    private List<WorkoutNote> workoutNotes;

    public List<WorkoutNote> getWorkoutNotes(Context context) {
        this.workoutNotes = WorkoutNoteLocalRepository.getInstance().getSavedNotes(context);
        return workoutNotes;
    }

    public WorkoutNote findworkoutNote(Context context,String date){
       this.workoutNote = WorkoutNoteLocalRepository.getInstance().findNote(context,date);
       return workoutNote;
    }

    public void updateNote(Context context,Long id, String content){
       AsyncTask.execute(()-> WorkoutNoteLocalRepository.getInstance().updateNote(context,id,content));
    }

//    public void setWorkoutNotes(List<WorkoutNote> workoutNotes) {
//        this.workoutNotes = workoutNotes;
//    }

    public WorkoutNoteViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveWorkoutNote(Context c, String date, String name, String split, String content){
        this.workoutNote = new WorkoutNote(date,name,split,content);
        WorkoutNoteLocalRepository.getInstance().addNoteToLocals(c,this.workoutNote);

    }
}

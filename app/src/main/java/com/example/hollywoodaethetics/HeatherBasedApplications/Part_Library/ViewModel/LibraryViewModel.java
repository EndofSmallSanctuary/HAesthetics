package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Data.ExerciseResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.LibraryFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Repository.LibraryRepository;

import java.util.List;

public class LibraryViewModel extends AndroidViewModel {

    private static List<ExerciseResponce> exerciseResponceList = null;
    private LibraryFragment fragmentlinked;
    public void setFragmentlinked(LibraryFragment fragmentlinked) {
        this.fragmentlinked = fragmentlinked;
    }


    public void passFragment(){
        fragmentlinked.passLoading(this.exerciseResponceList);
    }

    public void RequestResponceList(){
        if(exerciseResponceList!=null){
            passFragment();
            return;
        } else {
            LibraryRepository.getInstance().getAllExercises(this);
            return;
        }
    }

    public List<ExerciseResponce> getExerciseResponceList() {
        if(exerciseResponceList!=null){
            return exerciseResponceList;
        }
        else return null;
    }

    public void setExerciseResponceList(List<ExerciseResponce> exerciseResponceList) {
        this.exerciseResponceList = exerciseResponceList;
    }

    public LibraryViewModel(@NonNull Application application) {
        super(application);
    }

}

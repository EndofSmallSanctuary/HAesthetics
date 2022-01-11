package com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.Data.ExerciseDetailsResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.Repository.ExerciseDetailsRepository;

public class ExerciseDetailsViewModel extends AndroidViewModel {
    private MutableLiveData<ExerciseDetailsResponce> personalstats = new MutableLiveData();

    public MutableLiveData<ExerciseDetailsResponce> getPersonalstats(String id, String category, String exercise) {
        ExerciseDetailsRepository.getInstance().getExerciseDetails(id,exercise,category,this);
        return personalstats;
    }

    public void setPersonalstats(ExerciseDetailsResponce personalstats) {
        this.personalstats.postValue(personalstats);
    }

    public void updatePersonalstats(String id, String category, String exercise, float stat, int reps){
        ExerciseDetailsRepository.getInstance().updateExerciseDetails(id,exercise,category,stat,reps);
    }

    public ExerciseDetailsViewModel(@NonNull Application application) {
        super(application);
    }
}

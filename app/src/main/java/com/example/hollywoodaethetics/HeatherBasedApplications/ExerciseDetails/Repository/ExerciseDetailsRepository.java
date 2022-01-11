package com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.Repository;

import android.util.Log;

import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.Api.ExerciseDetailsApi;
import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.Data.ExerciseDetailsResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.ViewModel.ExerciseDetailsViewModel;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseDetailsRepository {
    ExerciseDetailsApi exerciseDetailsApi;
   private static volatile ExerciseDetailsRepository instance = new ExerciseDetailsRepository();

    private  ExerciseDetailsRepository(){};

    public static ExerciseDetailsRepository getInstance(){
        if(instance==null) instance = new ExerciseDetailsRepository();
        instance.exerciseDetailsApi = ExternalConfiguration.getRetrofitInstance().create(ExerciseDetailsApi.class);
        return instance;
    }

    public void updateExerciseDetails(String id, String exercise, String category, Float stat, Integer reps){
        Call<Void> call = exerciseDetailsApi.setExercisestat(id,exercise,category,stat,reps);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Dogs",t.getLocalizedMessage());
            }
        });
    }

    public void getExerciseDetails(String id, String exercise, String category,  ExerciseDetailsViewModel v){
        Call<ExerciseDetailsResponce> call = exerciseDetailsApi.getExercisestat(id,exercise,category);
        call.enqueue(new Callback<ExerciseDetailsResponce>() {
            @Override
            public void onResponse(Call<ExerciseDetailsResponce> call, Response<ExerciseDetailsResponce> response) {
                v.setPersonalstats(response.body());
            }

            @Override
            public void onFailure(Call<ExerciseDetailsResponce> call, Throwable t) {
                Log.d("Dogs",t.getLocalizedMessage());
            }
        });


    }
}

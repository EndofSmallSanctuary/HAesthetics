package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Repository;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Api.LibraryApi;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Data.ExerciseResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.ViewModel.LibraryViewModel;
import com.example.hollywoodaethetics.Survey.Api.SurveyAPIinterface;
import com.example.hollywoodaethetics.Survey.Repository.SurveyRepository;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LibraryRepository {
   LibraryApi libraryApi;
    private static volatile LibraryRepository instance = new LibraryRepository();

    private LibraryRepository() {

    }

    public static LibraryRepository getInstance() {
        if (instance == null) {
            instance = new LibraryRepository();
        }
        instance.libraryApi = ExternalConfiguration.getRetrofitInstance().create(LibraryApi.class);
        return instance;
    }



    public void getAllExercises(LibraryViewModel v){
        Call<List<ExerciseResponce>> call = libraryApi.getexerciselist();
        call.enqueue(new Callback<List<ExerciseResponce>>() {
            @Override
            public void onResponse(Call<List<ExerciseResponce>> call, Response<List<ExerciseResponce>> response) {
                v.setExerciseResponceList(response.body());
                v.passFragment();
            }

            @Override
            public void onFailure(Call<List<ExerciseResponce>> call, Throwable t) {
                Log.d("Http",t.getLocalizedMessage());
            }
        });
    }
}

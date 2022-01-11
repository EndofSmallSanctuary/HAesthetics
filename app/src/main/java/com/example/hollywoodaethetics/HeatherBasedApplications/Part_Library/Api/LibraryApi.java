package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Api;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Data.ExerciseResponce;
import com.example.hollywoodaethetics.Survey.Data.Physique;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LibraryApi {

    @GET("/api/getexerciseslist.php")
        Call<List<ExerciseResponce>> getexerciselist();



}

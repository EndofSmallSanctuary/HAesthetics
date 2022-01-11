package com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.Api;

import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.Data.ExerciseDetailsResponce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ExerciseDetailsApi {

    @FormUrlEncoded
    @POST("api/exercisedetails/getexercisestat.php")
    Call<ExerciseDetailsResponce> getExercisestat(
            @Field("user_id") String user_id,
            @Field("exercise") String exercise,
            @Field("category") String category
    );

    @FormUrlEncoded
    @POST("api/exercisedetails/updateexercisestat.php")
    Call<Void> setExercisestat(
            @Field("user_id") String user_id,
            @Field("exercise") String exercise,
            @Field("category") String category,
            @Field("stat") float stat,
            @Field("reps") int reps
    );
}

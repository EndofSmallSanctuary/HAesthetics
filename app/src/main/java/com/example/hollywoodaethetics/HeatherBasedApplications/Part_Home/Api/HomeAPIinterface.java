package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Api;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyWorkoutResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.YoutubeResponce;
import com.example.hollywoodaethetics.Survey.Data.Physique;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HomeAPIinterface {

    //Basic HeatherAPI methods

    @FormUrlEncoded
    @POST("/api/heatherbased/daily_route.php")
    Call<DailyWorkoutResponce> getdaily (
            @Field("user_id") String user_id,
            @Field("physique") String physique,
            @Field("split") String split
    );

    @GET("/api/get_youtube.php")
    Call<List<YoutubeResponce>> getYoutube();
}

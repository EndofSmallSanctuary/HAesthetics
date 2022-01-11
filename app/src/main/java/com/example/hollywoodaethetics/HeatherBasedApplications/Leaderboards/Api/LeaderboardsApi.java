package com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Api;

import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Data.LeaderResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LeaderboardsApi {

    @FormUrlEncoded
    @POST("api/getLeaders.php")
    Call<List<LeaderResponce>> getLeaderbyCategory(
            @Field("category") String category
    );
}

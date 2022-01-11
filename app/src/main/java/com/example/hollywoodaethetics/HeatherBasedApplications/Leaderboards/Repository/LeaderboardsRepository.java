package com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Repository;

import android.util.Log;

import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Api.LeaderboardsApi;
import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Data.LeaderResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.ViewModel.LeaderboardsViewModel;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardsRepository {
    LeaderboardsApi leaderboardsApi;
    private static volatile LeaderboardsRepository instance = new LeaderboardsRepository();

    private  LeaderboardsRepository(){};

    public static LeaderboardsRepository getInstance(){
        if(instance==null) instance = new LeaderboardsRepository();
        instance.leaderboardsApi = ExternalConfiguration.getRetrofitInstance().create(LeaderboardsApi.class);
        return instance;
    }

    public void getLeaderByCategory(LeaderboardsViewModel v, String category){
        Call<List<LeaderResponce>> call = leaderboardsApi.getLeaderbyCategory(category);
        call.enqueue(new Callback<List<LeaderResponce>>() {
            @Override
            public void onResponse(Call<List<LeaderResponce>> call, Response<List<LeaderResponce>> response) {
                v.setLeaderslist(response.body());
            }

            @Override
            public void onFailure(Call<List<LeaderResponce>> call, Throwable t) {
                Log.d("Http",t.getLocalizedMessage());
            }
        });
    }

}

package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Repository;

import android.util.Log;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Api.HomeAPIinterface;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyWorkoutResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.YoutubeResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.ViewModel.HomeViewModel;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    HomeAPIinterface homeAPIinterface;

    private static volatile HomeRepository instance = new HomeRepository();

    private HomeRepository() {

    }

    public static HomeRepository getInstance() {
        if (instance == null) {
            instance = new HomeRepository();
        }
        instance.homeAPIinterface = ExternalConfiguration.getRetrofitInstance().create(HomeAPIinterface.class);
        return instance;
    }



    public void getDaily(HomeViewModel v){
        Call<DailyWorkoutResponce> call = homeAPIinterface.getdaily(v.user_id,v.physique,v.split);
        call.enqueue(new Callback<DailyWorkoutResponce>() {
            @Override
            public void onResponse(Call<DailyWorkoutResponce> call, Response<DailyWorkoutResponce> response) {
               // v.dailyLiveData.postValue(response.body());
                v.dailyLiveData = response.body();
                if(response.body().isRest) v.setRest();
                if(!(response.body().isRest) && !(response.body().isTaken))
                    //AsyncTask.execute(()->HomeLocalRepository.getInstance().addSingleDaily(response.body(),v.getApplication().getApplicationContext()));
                    HomeLocalRepository.getInstance().addSingleDaily(response.body(),v.getApplication().getApplicationContext());
                Log.d("dogs",Boolean.toString(response.body().isTaken));
                v.passfragment();
            }

            @Override
            public void onFailure(Call<DailyWorkoutResponce> call, Throwable t) {
                Log.d("Http",t.getLocalizedMessage());
            }
        });
    }

    public void getYoutube(HomeViewModel v){
        Call<List<YoutubeResponce>> call  = homeAPIinterface.getYoutube();
        call.enqueue(new Callback<List<YoutubeResponce>>() {
            @Override
            public void onResponse(Call<List<YoutubeResponce>> call, Response<List<YoutubeResponce>> response) {
                v.youtubeResponces = response.body();
                v.passfragment();
            }

            @Override
            public void onFailure(Call<List<YoutubeResponce>> call, Throwable t) {

            }
        });
    }


}

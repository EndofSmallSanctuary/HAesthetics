package com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Repository;

import android.util.Log;

import androidx.lifecycle.AndroidViewModel;

import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Api.exInterpretationApi;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Data.exInterpretationResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.ViewModel.exInterpretationViewModel;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class exInterpretationRepository {
    exInterpretationApi exInterpretationApi;
    private static volatile exInterpretationRepository instance = new exInterpretationRepository();

    private exInterpretationRepository(){};

    public static exInterpretationRepository getInstance(){
        if(instance==null) instance = new exInterpretationRepository();
        instance.exInterpretationApi = ExternalConfiguration.getRetrofitInstance().create(exInterpretationApi.class);
        return instance;
    }

    public void getReadyExList(String exids, exInterpretationViewModel v){
        Call<List<exInterpretationResponce>> call = exInterpretationApi.getReadyExList(exids);
        call.enqueue(new Callback<List<exInterpretationResponce>>() {
            @Override
            public void onResponse(Call<List<exInterpretationResponce>> call, Response<List<exInterpretationResponce>> response) {
                v.setYoutubeDetailsResponce(response.body());
                Log.d("wise",response.body().get(0).img);
            }

            @Override
            public void onFailure(Call<List<exInterpretationResponce>> call, Throwable t) {
                Log.d("Http",t.getLocalizedMessage());
            }
        });
    }
}

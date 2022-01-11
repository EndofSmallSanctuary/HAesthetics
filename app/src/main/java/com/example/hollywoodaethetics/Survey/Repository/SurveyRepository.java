package com.example.hollywoodaethetics.Survey.Repository;

import android.util.Log;

import com.example.hollywoodaethetics.Survey.Api.SurveyAPIinterface;
import com.example.hollywoodaethetics.Survey.Data.Physique;
import com.example.hollywoodaethetics.Survey.SurveyActivity;
import com.example.hollywoodaethetics.Survey.ViewModel.SurveyViewModel;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SurveyRepository {
    SurveyAPIinterface surveyApiInterface;
    private static volatile SurveyRepository instance = new SurveyRepository();

    private SurveyRepository() {

    }

    public static SurveyRepository getInstance() {
        if (instance == null) {
            instance = new SurveyRepository();
        }
        instance.surveyApiInterface = ExternalConfiguration.getRetrofitInstance().create(SurveyAPIinterface.class);
        return instance;
    }

    public void savebio(SurveyViewModel viewModel) {

        RequestBody[] userbody = viewModel.user.getToSendParameters();
        RequestBody[] physiquebody = viewModel.physique.getToSendParameters();
        RequestBody[] keyliftsbody = viewModel.keyLifts.geToSendParameters();

        Call<String> call = surveyApiInterface.savebio(userbody[0],userbody[1],userbody[2],userbody[3],userbody[4],userbody[5],userbody[6],userbody[7],userbody[8],
                physiquebody[0],physiquebody[1],keyliftsbody[0],keyliftsbody[1],keyliftsbody[2],keyliftsbody[3],viewModel.avatar.avatar);

        //Порабоать над условивем :)
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("Dogs",Integer.toString(response.body().length()));
                if(response.body().length()<40) {viewModel.declineactivity(response.body());}
                else {viewModel.passactivity(response.body());}
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Dogs", t.toString());
            }
        });

    }

    public void getphysique(float physiquecode,SurveyViewModel viewModel) {
        Call<Physique> call = surveyApiInterface.getphysique(physiquecode);
        call.enqueue(new Callback<Physique>() {
            @Override
            public void onResponse(Call<Physique> call, Response<Physique> response) {
                if (!response.isSuccessful()) {
                    Log.d("Dogs", "Bad response");
                    return;
                }
                Log.d("Dogs", response.body().physiquename);
                viewModel.physique = new Physique(response.body().physiquename, response.body().splitname);
                viewModel.postupdateacitvity();
                Log.d("Dogs", viewModel.physique.physiquename);
            }

            @Override
            public void onFailure(Call<Physique> call, Throwable t) {
                Log.d("Dogs", t.getLocalizedMessage());
            }
        });

    }
}

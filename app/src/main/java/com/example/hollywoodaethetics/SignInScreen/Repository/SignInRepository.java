package com.example.hollywoodaethetics.SignInScreen.Repository;

import android.util.Log;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.SignInScreen.Api.SignInApi;
import com.example.hollywoodaethetics.SignInScreen.Data.SignInResponce;
import com.example.hollywoodaethetics.SignInScreen.ViewModel.SignInViewModel;
import com.example.hollywoodaethetics.Survey.Api.SurveyAPIinterface;
import com.example.hollywoodaethetics.Survey.Repository.SurveyRepository;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInRepository {
    SignInApi signInApi;

    private static volatile SignInRepository instance = new SignInRepository();

    private SignInRepository() {

    }

    public static SignInRepository getInstance() {
        if (instance == null) {
            instance = new SignInRepository();
        }
        instance.signInApi = ExternalConfiguration.getRetrofitInstance().create(SignInApi.class);
        return instance;
    }

    public void makeASignIn(SignInViewModel v){
        Call<SignInResponce> call = signInApi.signIn(v.getLoginfield(),v.getPassfield());
        call.enqueue(new Callback<SignInResponce>() {
            @Override
            public void onResponse(Call<SignInResponce> call, Response<SignInResponce> response) {
              if(response.body().fail) v.declineActivity(v.getActivityLinked().getApplication().getString(R.string.error_wrongpass));
                    else {
                  v.setSignInResponce(response.body());
                  v.passActivity();
              }

            }

            @Override
            public void onFailure(Call<SignInResponce> call, Throwable t) {
                Log.d("Http",t.getLocalizedMessage());
            }
        });
    }
}

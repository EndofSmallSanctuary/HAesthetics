package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Repository;

import android.util.Log;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Api.LegendaryApi;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Data.AestheticsResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.ViewModel.LegendaryViewModel;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LegendaryRepository {

    LegendaryApi legendaryApi;
    private static volatile LegendaryRepository instance = new LegendaryRepository();

    private LegendaryRepository() {

    }

    public static LegendaryRepository getInstance() {
        if (instance == null) {
            instance = new LegendaryRepository();
        }
        instance.legendaryApi = ExternalConfiguration.getRetrofitInstance().create(LegendaryApi.class);
        return instance;
    }



    public void getAethetics(LegendaryViewModel v, String user_id){
        Log.d("Dogs",user_id);
        Call<List<AestheticsResponce>> call = legendaryApi.getAethethetics(user_id);
        call.enqueue(new Callback<List<AestheticsResponce>>() {
            @Override
            public void onResponse(Call<List<AestheticsResponce>> call, Response<List<AestheticsResponce>> response) {
                v.setAestheticsResponceList(response.body());
            }

            @Override
            public void onFailure(Call<List<AestheticsResponce>> call, Throwable t) {
                Log.d("Http",t.getLocalizedMessage());
            }
        });
    }

    public void setAethetics(AestheticsResponce e, LegendaryViewModel v){
        File file = new File(e.filepath);
        Call<String> call = legendaryApi.setAethetics(
                RequestBody.create(MediaType.parse("text/plain"),e.id),
                RequestBody.create(MediaType.parse("text/plain"),e.user_id),
                RequestBody.create(MediaType.parse("text/plain"), e.name),
                RequestBody.create(MediaType.parse("text/plain"), e.info),
                RequestBody.create(MediaType.parse("text/plain"),Integer.toString(e.height)),
                RequestBody.create(MediaType.parse("text/plain"),Integer.toString(e.weight)),
                RequestBody.create(MediaType.parse("text/plain"),e.tag),
                RequestBody.create(MediaType.parse("text/plain"),e.components),
                MultipartBody.Part.createFormData("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/*"), file)));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.body().equals("Aesthetics added successfully")){
                    v.DeclineActivity(response.body());
                } else v.PassActivity(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Http",t.getLocalizedMessage());
            }
        });

    }
}

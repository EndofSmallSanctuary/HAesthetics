package com.example.hollywoodaethetics.Utilities;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExternalConfiguration {

    private static String baseURL = "http://192.168.1.120";
    private static Retrofit retrofit;


    private ExternalConfiguration(){}

    public static Retrofit getRetrofitInstance(){
        if(retrofit!=null) return retrofit;
        else {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
        }
    }
}

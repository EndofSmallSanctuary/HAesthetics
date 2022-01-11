package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Api;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Data.AestheticsResponce;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface LegendaryApi {

    @FormUrlEncoded
    @POST("/api/getAethetics.php")
    Call<List<AestheticsResponce>> getAethethetics(
            @Field("user_id") String user_id
    );

    @Multipart
    @POST("/api/setAethetics.php")
    Call<String> setAethetics(
            @Part("id") RequestBody id,
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("info") RequestBody info,
            @Part("height") RequestBody height,
            @Part("weight") RequestBody weight,
            @Part("tag") RequestBody tag,
            @Part("components") RequestBody components,
            @Part MultipartBody.Part filePart
    );
}

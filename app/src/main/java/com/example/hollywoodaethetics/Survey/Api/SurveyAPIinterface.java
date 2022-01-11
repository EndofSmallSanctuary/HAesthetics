package com.example.hollywoodaethetics.Survey.Api;

import com.example.hollywoodaethetics.Survey.Data.KeyLifts;
import com.example.hollywoodaethetics.Survey.Data.Physique;
import com.example.hollywoodaethetics.Survey.Data.User;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface SurveyAPIinterface {

    //Basic HeaterAPI methods

    @FormUrlEncoded
    @POST("/api/heatherbased/get_physique.php")
    Call<Physique> getphysique ( @Field("physiquecode") float physiquecode);



    @Multipart
    @POST("/api/save_bio.php")
    Call<String> savebio(
            @Part("id") RequestBody id,
            @Part("gender") RequestBody gender,
            @Part("height") RequestBody height,
            @Part("weight") RequestBody weight,
            @Part("metric") RequestBody metric,
            @Part("username") RequestBody username,
            @Part("fullname") RequestBody fullname,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("physique") RequestBody physique,
            @Part("split") RequestBody split,
            @Part("incline") RequestBody incline,
            @Part("chinups") RequestBody chinups,
            @Part("deadlift") RequestBody deadlift,
            @Part("press") RequestBody press,
            @Part MultipartBody.Part filePart
    );



//    @FormUrlEncoded
//    @POST("/api/save_bio.php")
//    Call<String> savebio(
//            @Field("id") String id,
//            @Field("gender") String gender,
//            @Field("height") int height,
//            @Field("weight") int weight,
//            @Field("metric") String metric,
//            @Field("username") String username,
//            @Field("fullname") String fullname,
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("physique") String physique,
//            @Field("split") String split,
//            @Field("incline") float incline,
//            @Field("chinups") float chinups,
//            @Field("deadlift") float deadlift,
//            @Field("press") float press
//    );



//    @Multipart
//    @POST("/api/save_avatar.php")
//    Call<Void> savepic (
//            @Header("Auth") String auth,
//            @Part MultipartBody.Part filePart
//    );


}

package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Api;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Data.UserG;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Data.UserT;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProfileApi {
    @Multipart
    @POST("/api/profileupdates/updateavatar.php")
    Call<String> updateavatar(
            @Part("id") RequestBody id,
            @Part("username") RequestBody username,
            @Part MultipartBody.Part filePart
    );
    @Multipart
    @POST("/api/profileupdates/updatebackground.php")
    Call<String> updatebackground(
            @Part("id") RequestBody id,
            @Part("username") RequestBody username,
            @Part MultipartBody.Part filePart
    );

    @FormUrlEncoded
    @POST("/api/profileupdates/updateprofile.php")
    Call<String> updateprofile(
                    @Field("id") String id,
                    @Field("username") String username,
                    @Field("fullname") String fullname,
                    @Field("height") int height,
                    @Field("weight") int weight,
                    @Field("email") String email,
                    @Field("incline") float incline,
                    @Field("chinups") float chinups,
                    @Field("deadlift") float dealift,
                    @Field("press") float press

            );
    @FormUrlEncoded
    @POST("/api/profileupdates/updateprofile_nocheck.php")
    Call<String> updateprofilenocheck(
            @Field("id") String id,
            @Field("fullname") String fullname,
            @Field("height") int height,
            @Field("weight") int weight,
            @Field("incline") float incline,
            @Field("chinups") float chinups,
            @Field("deadlift") float dealift,
            @Field("press") float press

    );
    @FormUrlEncoded
    @POST("api/getprofiledata.php")
    Call<UserG> fetchprofile(
            @Field("id") String id

    );







}

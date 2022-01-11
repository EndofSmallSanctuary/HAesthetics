package com.example.hollywoodaethetics.SignInScreen.Api;

import com.example.hollywoodaethetics.SignInScreen.Data.SignInResponce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignInApi {

    @FormUrlEncoded
    @POST("/api/signIn.php")
    Call<SignInResponce> signIn(
                @Field("loginfield") String loginfield,
                @Field("passfield") String passfield
                );


}

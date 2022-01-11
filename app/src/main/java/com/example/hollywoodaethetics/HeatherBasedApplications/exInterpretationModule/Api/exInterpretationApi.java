package com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Api;

import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Data.exInterpretationResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface exInterpretationApi {

    @FormUrlEncoded
    @POST("api/exidsinterpretation.php")
    Call<List<exInterpretationResponce>> getReadyExList(
            @Field("exids") String exids
    );
}

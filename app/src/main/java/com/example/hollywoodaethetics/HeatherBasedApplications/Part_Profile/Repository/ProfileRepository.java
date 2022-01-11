package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Repository;

import android.util.Log;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Api.ProfileApi;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Data.UserG;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Data.UserT;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.ViewModel.ProfileViewModel;
import com.example.hollywoodaethetics.Utilities.ExternalConfiguration;

import java.io.File;

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

public class ProfileRepository {
        ProfileApi profileApi;

        private static volatile ProfileRepository instance = new ProfileRepository();

        private ProfileRepository() {

        }

        public static ProfileRepository getInstance() {
            if (instance == null) {
                instance = new ProfileRepository();
            }
            instance.profileApi = ExternalConfiguration.getRetrofitInstance().create(ProfileApi.class);
            return instance;
        }

        public void getProfileInfo(ProfileViewModel v, String id){
            Call<UserG> call = profileApi.fetchprofile(id);
            call.enqueue(new Callback<UserG>() {
                @Override
                public void onResponse(Call<UserG> call, Response<UserG> response) {
                    if(response.isSuccessful()) v.setProfiledata(response.body());
                }

                @Override
                public void onFailure(Call<UserG> call, Throwable t) {
                    Log.d("Http",t.getLocalizedMessage());
                }
            });
        }

        public void updateProfile(UserT u, boolean ischeckneeded, ProfileViewModel v){
            if(ischeckneeded) {
                Call<String> call = profileApi.updateprofile(u.id, u.username, u.fullname, u.height, u.weight, u.email, u.incline, u.chinups
                        , u.dealift, u.press);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("Your changes saved")) v.showSuccess(response.body());
                        else v.showError(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("Http", t.getLocalizedMessage());
                    }
                });
            }
            else {
                Call<String> call = profileApi.updateprofilenocheck(u.id, u.fullname, u.height, u.weight, u.incline, u.chinups
                        , u.dealift, u.press);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("Your changes saved")) v.showSuccess(response.body());
                        else v.showError(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("Http", t.getLocalizedMessage());
                    }
                });
            }
        }

        public void updateAvatar(String filepath, String id, String username, ProfileViewModel v,boolean a) {
            File file = new File(filepath);
            Log.d("Avatar", filepath);
            if (a) {
                Call<String> call = profileApi.updateavatar(RequestBody.create(MediaType.parse("text/plain"), id),
                        RequestBody.create(MediaType.parse("text/plain"), username),
                        MultipartBody.Part.createFormData("file", file.getName(),
                                RequestBody.create(MediaType.parse("image/*"), file)));

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response!=null) {
                            v.setlocalAvatar(response.body(),true);
                            v.showSuccess("Your changes saved");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("Http", t.getLocalizedMessage());
                    }
                });
            }
            else {
                Call<String> call = profileApi.updatebackground(RequestBody.create(MediaType.parse("text/plain"), id),
                        RequestBody.create(MediaType.parse("text/plain"), username),
                        MultipartBody.Part.createFormData("file", file.getName(),
                                RequestBody.create(MediaType.parse("image/*"), file)));

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response!=null) {
                            v.showSuccess("Your changes saved");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("Http", t.getLocalizedMessage());
                    }
                });
            }
        }
}

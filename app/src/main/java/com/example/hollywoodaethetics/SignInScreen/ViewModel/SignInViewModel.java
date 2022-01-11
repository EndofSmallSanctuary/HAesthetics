package com.example.hollywoodaethetics.SignInScreen.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.hollywoodaethetics.SignInScreen.Data.SignInResponce;
import com.example.hollywoodaethetics.SignInScreen.Repository.SignInRepository;
import com.example.hollywoodaethetics.SignInScreen.SignInActivity;

public class SignInViewModel extends AndroidViewModel {
    private SignInActivity activitylinked;
    private String loginfield;
    private String passfield;
    private  SignInResponce signInResponce;



    public void setSignInResponce(SignInResponce signInResponce) {
       this.signInResponce = signInResponce;
    }

    public String getLoginfield() {
        return loginfield;
    }

    public String getPassfield() {
        return passfield;
    }

    public void signIn(String loginfield, String passfield) {
        this.loginfield = loginfield;
        this.passfield = passfield;
        SignInRepository.getInstance().makeASignIn(this);
    }


    public void setActivitylinked(SignInActivity activitylinked) {
        this.activitylinked = activitylinked;
    }

    public SignInActivity getActivityLinked(){
        return this.activitylinked;
    }

    public void passActivity(){

        SharedPreferences preferences = this.activitylinked.getSharedPreferences("UserLocals", Context.MODE_PRIVATE);
        preferences.edit().putString("id",signInResponce.id).apply();
        preferences.edit().putString("split",signInResponce.split).apply();
        preferences.edit().putString("physique",signInResponce.physique).apply();
        preferences.edit().putString("avatar",signInResponce.avatar).apply();
        preferences.edit().putString("metric",signInResponce.metric).apply();
        preferences.edit().putString("username",signInResponce.username).apply();
        //preferences.edit().putString("background",signInResponce.background).apply();
        preferences.edit().putBoolean("loginstate",true).apply();


        activitylinked.ShowSuccess();
    }
    public void declineActivity(String message){
        activitylinked.ShowError(message);
    }

    public SignInViewModel(@NonNull Application application)
    {
        super(application);
    }
}

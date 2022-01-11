package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Data.UserG;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Data.UserT;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.ProfileEditActivity;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.ProfileFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Repository.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    ProfileEditActivity activitylinked;
    ProfileFragment fragmentlinked;
    UserT userT;
    private String id;
    private MutableLiveData<UserG> profiledata = new MutableLiveData<>();

    public MutableLiveData<UserG> getProfiledata() {
        return profiledata;
    }

    public void setProfiledata(UserG profiledata) {
        this.profiledata.postValue(profiledata);
    }

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }


    public void setProfileLinked(ProfileFragment fragment,String id){
        this.fragmentlinked = fragment;
        this.id = id;
    }

    public void setActivitylinked(ProfileEditActivity activitylinked,String id){
        this.activitylinked = activitylinked;
        this.id = id;
        Log.d("Dogs",id);
    }

    public void setlocalAvatar(String avatarURL,boolean a){
        if(a) {
            activitylinked.getSharedPreferences("UserLocals", Context.MODE_PRIVATE).edit()
                    .putString("avatar", avatarURL).apply();
        }

    }
//    public void setlocalUserT(String responce){
//
//        activitylinked.getSharedPreferences("UserLocals", Context.MODE_PRIVATE).edit()
//                    .putString("username", userT.username).apply();
//
//        showSuccess(responce);
//
//    }

    public void showError(String message){
        activitylinked.ShowError(message);
    }
    public void showSuccess(String message){
        activitylinked.ShowSuccess(message);
    }

    public void getProfile(){
        ProfileRepository.getInstance().getProfileInfo(this,this.id);
    }

    public void updateavatar(String username,String id,String filepath){
        ProfileRepository.getInstance().updateAvatar(filepath,id,username,this,true);
    }
    public void updatebackground(String username,String id,String filepath){
        ProfileRepository.getInstance().updateAvatar(filepath,id,username,this,false);
    }


    public void updateprofile(String username, String fullname, int height, int weight, String email,float incline,float chinups,float dealift,float press, boolean ischeckneeded){
        this.userT = new UserT(this.id, username, fullname, height, weight, email, incline, chinups, dealift, press);
        ProfileRepository.getInstance().updateProfile(userT, ischeckneeded,this);
    }


}

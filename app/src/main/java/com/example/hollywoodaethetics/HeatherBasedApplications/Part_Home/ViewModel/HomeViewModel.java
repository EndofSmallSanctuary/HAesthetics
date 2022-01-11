package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyEntity;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyWorkoutResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.YoutubeResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.HomeFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Repository.HomeLocalRepository;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Repository.HomeRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private int responcereadyflag = 0;

    public  String user_id;
    public  HomeFragment fragmentlinked;
    public String physique;
    public String split;
    //public MutableLiveData<DailyWorkoutResponce> dailyLiveData = new MutableLiveData<DailyWorkoutResponce>();
    public DailyWorkoutResponce dailyLiveData;
    public List<YoutubeResponce> youtubeResponces;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public  void setLinkedFragment(HomeFragment fragment){
        fragmentlinked = fragment;
        user_id = fragment.getActivity().getSharedPreferences("UserLocals", Context.MODE_PRIVATE)
                .getString("id","0");
        physique = fragment.getActivity().getSharedPreferences("UserLocals",Context.MODE_PRIVATE)
                .getString("physique","");
        split = fragment.getActivity().getSharedPreferences("UserLocals",Context.MODE_PRIVATE)
                .getString("split","");
        Log.d("Dogs",user_id);
    }

    public List<DailyEntity> getLocalDailies(){
        return HomeLocalRepository.getInstance().getSavedDailies(this.getApplication().getApplicationContext());
    }

    public void getYoutube(){
        HomeRepository.getInstance().getYoutube(this);
    }

    public void setRest(){
        fragmentlinked.setRest();
    }

    public void passfragment(){
        responcereadyflag++;
        if (responcereadyflag==2) {
            responcereadyflag=0;
            fragmentlinked.passLoading();
        }
    }



    public void getDailyWorkout(){
        HomeRepository.getInstance().getDaily(this);
    }


}

package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Data.AestheticsResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.LegendaryFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.LegendaryeditActivity;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Repository.LegendaryRepository;

import java.util.ArrayList;
import java.util.List;

public class LegendaryViewModel extends AndroidViewModel {

    private AestheticsResponce aestheticsResponceToSend;
    private LegendaryeditActivity activitylinked;
    private LegendaryFragment fragmentlinked;
    private MutableLiveData<List<AestheticsResponce>> aestheticsResponceList = new MutableLiveData<>();

    public MutableLiveData<List<AestheticsResponce>> getAestheticsResponceList() {
        return aestheticsResponceList;
    }

    public void setAestheticsResponceList(List<AestheticsResponce> aestheticsResponceList) {
        this.aestheticsResponceList.postValue(aestheticsResponceList);
    }

    public void setFragmentlinked(LegendaryFragment fragmentlinked) {
        this.fragmentlinked = fragmentlinked;
    }


    public void setActivityLinked(AppCompatActivity activity){
        this.activitylinked = (LegendaryeditActivity) activity;
    }

    public void PassActivity(String message){
        activitylinked.ShowSuccess(message);
    }
    public void DeclineActivity(String message){
        activitylinked.ShowError(message);
    }
    //public void PassFragment(String message)


    public LegendaryViewModel(@NonNull Application application) {
        super(application);
    }

    public void setAesthericsResponceToSend(String user_id, String name, String info, String imglink, String components, int height, int weight, String tag, String filepath) {
        this.aestheticsResponceToSend = new AestheticsResponce(name, info, imglink, components, height, weight, tag);
        setAesthericsResponceToSendFile(filepath,user_id);
        LegendaryRepository.getInstance().setAethetics(aestheticsResponceToSend,this);
    }

    public void setAesthericsResponceToSendFile(String filepath,String user_id){
        Log.d("v","Trying to set");
        this.aestheticsResponceToSend.setFilepath(filepath,user_id);
        Log.d("v",this.aestheticsResponceToSend.filepath);
    }


    public void getAesthetics(String user_id) {
        LegendaryRepository.getInstance().getAethetics(this,user_id);
    }
}

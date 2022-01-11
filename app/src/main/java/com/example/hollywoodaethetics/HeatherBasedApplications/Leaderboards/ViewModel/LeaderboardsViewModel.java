package com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Data.LeaderResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Repository.LeaderboardsRepository;

import java.util.List;

public class LeaderboardsViewModel extends AndroidViewModel {

    private MutableLiveData<List<LeaderResponce>> leaderslist = new MutableLiveData();

    public MutableLiveData<List<LeaderResponce>> getLeaderslist() {
        return leaderslist;
    }

    public void setLeaderslist(List<LeaderResponce> leaderslist) {
        this.leaderslist.postValue(leaderslist);
    }

    public void getLeadersByCategory(String category){
        LeaderboardsRepository.getInstance().getLeaderByCategory(this,category);
    }

    public LeaderboardsViewModel(@NonNull Application application) {
        super(application);
    }

}

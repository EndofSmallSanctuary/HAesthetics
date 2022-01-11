package com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Data.exInterpretationResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Repository.exInterpretationRepository;

import java.util.List;

public class exInterpretationViewModel extends AndroidViewModel {
    private MutableLiveData<List<exInterpretationResponce>> youtubeDetailsResponce = new MutableLiveData();

    public exInterpretationViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<exInterpretationResponce>> getYoutubeDetailsResponce(String exids) {
        exInterpretationRepository.getInstance().getReadyExList(exids,this);
        return youtubeDetailsResponce;
    }

    public void setYoutubeDetailsResponce(List<exInterpretationResponce> exInterpretationResponce) {
        this.youtubeDetailsResponce.postValue(exInterpretationResponce);
    }


}

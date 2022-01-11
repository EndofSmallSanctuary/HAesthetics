package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyEntity;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyWorkoutResponce;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class HomeLocalRepository  {
    private static volatile HomeLocalRepository instance = new HomeLocalRepository();

    private HomeLocalRepository() {

    }

    public static HomeLocalRepository getInstance() {
        if (instance == null) {
            instance = new HomeLocalRepository();
        }
        return instance;
    }

    public void addSingleDaily(DailyWorkoutResponce responce,Context context){
        SugarContext.init(context);
        DailyEntity newdaily = new DailyEntity(responce.workoutname,responce.imageurl,responce.splitname,responce.date,responce.exids,responce.reps);
        newdaily.save();
       // Log.d("dogs","added successfully");
    }

    public List<DailyEntity> getSavedDailies(Context context){
        SugarContext.init(context);
        //List<DailyEntity> returnlist = new ArrayList<>();
        try{
           return DailyEntity.listAll(DailyEntity.class);
        } catch (SQLiteException e){
            return new ArrayList<DailyEntity>();
        }
    }

    public void truncateDailies(Context context){
        SugarContext.init(context);
        DailyEntity.deleteAll(DailyEntity.class);
    }

}

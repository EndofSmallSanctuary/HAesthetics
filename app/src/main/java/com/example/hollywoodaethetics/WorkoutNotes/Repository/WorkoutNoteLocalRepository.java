package com.example.hollywoodaethetics.WorkoutNotes.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyEntity;
import com.example.hollywoodaethetics.WorkoutNotes.Data.WorkoutNote;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

public class WorkoutNoteLocalRepository {
    private static volatile WorkoutNoteLocalRepository instance = new WorkoutNoteLocalRepository();

    public static WorkoutNoteLocalRepository getInstance(){
        if(instance==null) instance = new WorkoutNoteLocalRepository();
        return instance;
    }

    public void addNoteToLocals(Context c, WorkoutNote w){
        SugarContext.init(c);
        AsyncTask.execute(()->w.save());
        Log.d("rec","note saved completely");
    }

    public WorkoutNote findNote(Context c, String date){
        SugarContext.init(c);
       try {
           WorkoutNote result = WorkoutNote.find(WorkoutNote.class,"date = ?", date).get(0);
           return result;
       } catch (Exception e){
           return null;
       }
    }

    public void updateNote(Context c,Long id,String content){
        SugarContext.init(c);
        WorkoutNote noteE = WorkoutNote.findById(WorkoutNote.class,id);
        noteE.content = content;
        noteE.save();


    }

    public List<WorkoutNote> getSavedNotes(Context context){
        SugarContext.init(context);
        //List<DailyEntity> returnlist = new ArrayList<>();
        try{
            return WorkoutNote.listAll(WorkoutNote.class);
        } catch (SQLiteException e){
            return new ArrayList<>();
        }
    }
}

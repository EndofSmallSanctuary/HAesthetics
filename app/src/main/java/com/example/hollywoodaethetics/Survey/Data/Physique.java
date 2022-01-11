package com.example.hollywoodaethetics.Survey.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Physique {


   public String physiquename;
    public String splitname;

    public Physique(String physiquename, String splitname){
        this.physiquename = physiquename;
        this.splitname = splitname;

    }

    public RequestBody[] getToSendParameters(){
        RequestBody[] params = new RequestBody[]{
                RequestBody.create(MediaType.parse("text/plain"),physiquename),
                RequestBody.create(MediaType.parse("text/plain"),splitname)
        };
        return params;
    }
}

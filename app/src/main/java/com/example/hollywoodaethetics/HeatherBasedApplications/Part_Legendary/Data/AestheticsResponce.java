package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Data;

import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class AestheticsResponce {
    public String name;
    public String info;
    public String imglink;
    public String components;
    public int height;
    public int weight;
    public String tag;


    public String id;
    public String user_id;
    public String filepath;

    public void setFilepath(String filepath, String user_id) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        Log.d("DOGS","Filepath set: "+filepath);
        this.filepath = filepath;
        this.user_id = user_id;
    }

    public AestheticsResponce(String name, String info, String imglink, String components, int height, int weight, String tag) {
        this.name = name;
        this.info = info;
        this.imglink = imglink;
        this.components = components;
        this.height = height;
        this.weight = weight;
        this.tag = tag;
    }



}

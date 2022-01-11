package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.Data;

import retrofit2.http.Field;

public class UserT {

    public String id;
   public String username;
    public String fullname;
   public int height;
   public int weight;
   public String email;
  public  float incline;
   public  float chinups;
  public  float dealift;
   public float press;

    public UserT(String id, String username, String fullname, int height, int weight, String email, float incline, float chinups, float dealift, float press) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.incline = incline;
        this.chinups = chinups;
        this.dealift = dealift;
        this.press = press;
    }
}

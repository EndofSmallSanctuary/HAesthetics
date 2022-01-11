package com.example.hollywoodaethetics.SignInScreen.Data;

public class SignInResponce {

    public SignInResponce(String id, String gender, int height, int weight, String metric, String username, String fullname,
                          String email, String avatar, String background, String physique, String split,
                          boolean fail, float incline, float chinups, float deadlift, float press) {
        this.id = id;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.metric = metric;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.avatar = avatar;
        this.background = background;
        this.physique = physique;
        this.split = split;
        this.fail = fail;
        this.incline = incline;
        this.chinups = chinups;
        this.deadlift = deadlift;
        this.press = press;
    }

    public String id;
    public String gender;
    public int height;
    public int weight;
    public String metric;
    public String username;
    public String fullname;
    public String email;
    public String avatar;
    public String background;
    public String physique;
    public String split;
    public boolean fail;

    public float incline;
    public float chinups;
    public float deadlift;
    public float press;


}

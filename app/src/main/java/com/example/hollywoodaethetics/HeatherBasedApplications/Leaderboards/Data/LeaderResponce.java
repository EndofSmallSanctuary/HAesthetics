package com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Data;

public class LeaderResponce {

    public String id;
    public String username;
    public String physique;
    public String avatar;
    public float result;

    public LeaderResponce(String id, String username, String physique, String avatar, float result) {
        this.id = id;
        this.username = username;
        this.physique = physique;
        this.avatar = avatar;
        this.result = result;
    }
}

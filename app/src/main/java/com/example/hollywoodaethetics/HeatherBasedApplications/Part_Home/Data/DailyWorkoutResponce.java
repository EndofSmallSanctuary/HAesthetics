package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data;

import java.util.ArrayList;
import java.util.List;

public class DailyWorkoutResponce {
    public String workoutname;
    public String imageurl;
    public String splitname;
    public String date;
    public boolean isRest;
    public boolean isTaken;
    public String exids;
    public String reps;
    public List<Exercises> exercises;



    public class Exercises{
        public String exname;
        public String imageurlex;
        public String category;
        public String description;
        public String reps;
        public String video;

        public Exercises(String exname, String imageurlex, String category, String description, String reps, String video) {
            this.exname = exname;
            this.imageurlex = imageurlex;
            this.category = category;
            this.description = description;
            this.reps = reps;
            this.video = video;
        }
    }

    public DailyWorkoutResponce(String workoutname, String splitname, String imageurl, String date, boolean isRest, boolean isTaken, String exids, String reps, List<Exercises> exercises) {
        this.workoutname = workoutname;
        this.splitname = splitname;
        this.imageurl = imageurl;
        this.exids = exids;
        this.reps = reps;
        this.date = date;
        this.isRest = isRest;
        this.isTaken = isTaken;
        this.exercises = exercises;
    }

}

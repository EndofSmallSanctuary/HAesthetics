package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data;

import com.orm.SugarRecord;

import java.util.List;

public class DailyEntity extends SugarRecord {

    public String workoutname;
    public String imageurl;
    public String splitname;
    public String date;
    public String exercises;
    public String reps;



//    public class Exercises{
//        public String exname;
//        public String imageurlex;
//        public String category;
//        public String description;
//        public String reps;
//        public String video;
//
//        public Exercises(String exname, String imageurlex, String category, String description, String reps, String video) {
//            this.exname = exname;
//            this.imageurlex = imageurlex;
//            this.category = category;
//            this.description = description;
//            this.reps = reps;
//            this.video = video;
//        }
//    }

    public DailyEntity(){

    }

    public DailyEntity(String workoutname, String imageurl, String splitname, String date, String exercises, String reps) {
        this.workoutname = workoutname;
        this.imageurl = imageurl;
        this.splitname = splitname;
        this.date = date;
        this.exercises = exercises;
        this.reps = reps;
    }
}

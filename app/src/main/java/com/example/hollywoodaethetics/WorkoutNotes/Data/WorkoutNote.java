package com.example.hollywoodaethetics.WorkoutNotes.Data;

import com.orm.SugarRecord;

public class WorkoutNote extends SugarRecord {
   public String name;
   public String split;
   public String date;
   public String content;

    public WorkoutNote(){};

    public WorkoutNote(String date, String name, String split, String content) {
        this.date = date;
        this.name = name;
        this.split = split;
        this.content = content;
    }
}

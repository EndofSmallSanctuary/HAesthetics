package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Data;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;


public class ExerciseResponce implements Parcelable {
  public  String title;
   public String img;
   public String category;
  public  String description;
  public String video;

    public ExerciseResponce(String title, String category, String img,  String description, String video) {
        this.title = title;
        this.category = category;
        this.img = img;
        this.description = description;
        this.video = video;
    }

    public ExerciseResponce(Parcel in){
        String[] data = new String[4];
        in.readStringArray(data);
        title = data[0];
        img = data[1];
        category = data[2];
        description = data[3];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{title,img,category,description});
    }

    public static final Parcelable.Creator<ExerciseResponce> CREATOR = new Parcelable.Creator<ExerciseResponce>(){

        @Override
        public ExerciseResponce createFromParcel(Parcel parcel) {
            return new ExerciseResponce(parcel);
        }

        @Override
        public ExerciseResponce[] newArray(int i) {
            return new ExerciseResponce[i];
        }
    };
}

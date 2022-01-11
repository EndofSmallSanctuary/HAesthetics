package com.example.hollywoodaethetics.Survey.ViewModel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.hollywoodaethetics.Survey.Data.Avatar;
import com.example.hollywoodaethetics.Survey.Data.KeyLifts;
import com.example.hollywoodaethetics.Survey.Data.Physique;
import com.example.hollywoodaethetics.Survey.Data.User;
import com.example.hollywoodaethetics.Survey.Repository.SurveyRepository;
import com.example.hollywoodaethetics.Survey.SurveyActivity;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SurveyViewModel extends ViewModel {

   public User user;
   public Physique physique;
   public KeyLifts keyLifts;
   public Avatar avatar;
   private SurveyActivity activitylinked;

    public SurveyViewModel(SurveyActivity activitylinked){
        this.activitylinked = activitylinked;
    }

   //-------------------------------------------------ForLINKEDACTIVITYMETHODS------------------------------------------------

   public String[] physiqueToArray(){
       String[] physiquearray = new String[2];
       physiquearray[0] = physique.physiquename;
       physiquearray[1] = physique.splitname;
       Log.d("Dogs",physiquearray[0]);
       return  physiquearray;
   }

   public void postupdateacitvity(){
       Bundle b = new Bundle();
       b.putStringArray("namesplit",physiqueToArray());
       this.activitylinked.PostCallupdate(b);
   }
   public void declineactivity(String message){
       this.activitylinked.ShowDecline(message);
   }

   public void passactivity(String avatarURL){
        SharedPreferences preferences = this.activitylinked.getSharedPreferences("UserLocals", Context.MODE_PRIVATE);
       preferences.edit().putString("id",user.id).apply();
       preferences.edit().putString("split",physique.splitname).apply();
       preferences.edit().putString("physique",physique.physiquename).apply();
       preferences.edit().putString("username",user.username).apply();
       preferences.edit().putString("avatar",avatarURL).apply();
       preferences.edit().putString("metric",user.metric).apply();
       preferences.edit().putBoolean("loginstate",true).apply();

        this.activitylinked.PassDecline();
    }


    //-------------------------------------------------/ForLINKEDACTIVITYMETHODS------------------------------------------------

    //-------------------------------------------------UserSetUps------------------------------------------------

    public void sendUserinfo(){SurveyRepository.getInstance().savebio(this); }

    public void setUpBio(Bundle b){
        user = new User();
        user.setUPBio(
                b.getString("gender"),
                b.getString("metric"),
                b.getInt("weight"),
                b.getInt("height")
        );
        if(user.metric.equals("metric"))
        keyLifts = new KeyLifts(
                b.getFloatArray("performance")[0],
                b.getFloatArray("performance")[1],
                b.getFloatArray("performance")[2],
                b.getFloatArray("performance")[3]
        ); else keyLifts = new KeyLifts(
                b.getFloatArray("performance")[0]/2.2f,
                b.getFloatArray("performance")[1]/2.2f,
                b.getFloatArray("performance")[2]/2.2f,
                b.getFloatArray("performance")[3]/2.2f
        );

       SurveyRepository.getInstance().getphysique(buildPhyqiueCode(b),this);

       if(user.metric=="imperial"){
           user.setUPBio(

                   b.getString("gender"),
                   b.getString("metric"),
                   b.getInt("weight"),
                   b.getInt("height")

           );
       }
    }

    public void setUpData(Bundle b){
        user.setUpData(
                b.getString("username"),
                b.getString("fullname"),
                b.getString("password"),
                b.getString("email")
        );
    }

    public void SetUpAvatar(String filepath){
       avatar = new Avatar(filepath);
    }

    //-------------------------------------------------/UserSetUps------------------------------------------------


    //-------------------------------------------------Utility-middleDataTransformFunctions------------------------------------------------

    private float buildPhyqiueCode(Bundle b){
        float physiquecode = 0.0f;
        if (user.metric == "imperial"){
            metricConvert();
        }
        if(user.gender == "Female"){
           return physiquecode = 99f;
        }
        if(user.height-user.weight<=100){
            Log.d("Http",Integer.toString(user.height));
            Log.d("Http",Integer.toString(user.weight));
            physiquecode+=20.5f;
            switch(b.getInt("fitnesslevel")) {
                case 0:
                case 1:{
                    return physiquecode;
                }
                case 2:
                case 3: {
                    physiquecode+=6f;
                    break;
                }
            }
        } else {
            switch(b.getInt("fitnesslevel")){
                case 0: {
                   return physiquecode=20.5f;
                }
                case 1: {
                   return physiquecode+=25.5f;
                }
                case 2:{
                    physiquecode+=10.5f;
                    break;
                }
                case 3:{
                    physiquecode+=0.5f;
                    break;
                }
            }
        }
        physiquecode+=analizegoals(b.getIntArray("goals"));
        physiquecode*=performancekoef();
        return physiquecode;

    }

    private void metricConvert(){
       float fraction;
       int integral;

       fraction = user.height * 0.1f;
       integral = (int)fraction;
       fraction = fraction%1;


       integral = integral*12 + (int)(fraction*10);
       user.height = (int)Math.round(integral*2.54);
       user.weight = (int)Math.round(user.weight /2.2);
    }

    private float performancekoef() {

        float koef = (this.keyLifts.chinups + this.keyLifts.incline + this.keyLifts.press + this.keyLifts.deadlift) / this.user.weight;
           if (koef < 3.9f) {
               koef = 1;
           } else if ((koef >= 3.9f) && (koef < 4.7f)) {
               koef = 0.7f;
           } else if ((koef >= 4.7f) && (koef < 5.6f)) {
               koef = 0.4f;
           } else if ((koef >= 5.6f)) {
               koef = 0.1f;
           }
           return koef;



    }
    private float analizegoals(int[] goals){
        float koef = 0.0f;
        if (goals[0]==1){koef+=0.5f;}
        if (goals[1]==1){koef+=6.1f;}
        if (goals[2]==1){koef+=20.5f;}
        return koef;
    }


//    private String passwordConvert(String pass){
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] messageDigest = md.digest(pass.getBytes());
//            BigInteger number = new BigInteger(1, messageDigest);
//            String md5 = number.toString(16);
//            while (md5.length() < 32)
//                md5 = "0" + md5;
//            return md5;
//        } catch (NoSuchAlgorithmException e){
//            Log.d("md5",e.getLocalizedMessage());
//            return null;
//        }
//    }

    //-------------------------------------------------/Utility-middleDataTransformFunctions------------------------------------------------


}

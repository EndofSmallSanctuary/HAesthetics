package com.example.hollywoodaethetics.Survey.Data;

import android.provider.MediaStore;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class User {
    //Core User-profile model
    public String id;
    public String gender;
    public int height;
    public int weight;
    public String metric;
    public String username;
    public String fullname;
    public String email;
    public String password;


    public User() {

    }

    public void setUPBio(String gender, String metric, int weight, int height) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        this.gender = gender;
        this.metric = metric;
        this.weight = weight;
        this.height = height;


    }

    public void setUpData(String username, String fullname, String password, String email) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
    }

    public RequestBody[] getToSendParameters(){
        RequestBody[] paramsarray = new RequestBody[]{
                RequestBody.create(MediaType.parse("text/plain"),id),
                RequestBody.create(MediaType.parse("text/plain"),gender),
                RequestBody.create(MediaType.parse("text/plain"),Integer.toString(height)),
                RequestBody.create(MediaType.parse("text/plain"),Integer.toString(weight)),
                RequestBody.create(MediaType.parse("text/plain"),metric),
                RequestBody.create(MediaType.parse("text/plain"),username),
                RequestBody.create(MediaType.parse("text/plain"),fullname),
                RequestBody.create(MediaType.parse("text/plain"),email),
                RequestBody.create(MediaType.parse("text/plain"),password)

        };

        return paramsarray;
    }
}
package com.example.hollywoodaethetics.Survey.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class KeyLifts {
    @Expose
    @SerializedName("incline") public float incline;
    @Expose
    @SerializedName("chinups") public float chinups;
    @Expose
    @SerializedName("deadlift") public float deadlift;
    @Expose
    @SerializedName("press") public float press;

    public KeyLifts(float incline, float chinup, float deadlift, float press){
        this.incline = incline;
        this.chinups = chinup;
        this.deadlift = deadlift;
        this.press = press;
    }

    public RequestBody[] geToSendParameters(){
        RequestBody[] params = new RequestBody[]{
                RequestBody.create(MediaType.parse("text/plain"),Float.toString(incline)),
                RequestBody.create(MediaType.parse("text/plain"),Float.toString(chinups)),
                RequestBody.create(MediaType.parse("text/plain"),Float.toString(deadlift)),
                RequestBody.create(MediaType.parse("text/plain"),Float.toString(press))
        };
        return params;
    }

}

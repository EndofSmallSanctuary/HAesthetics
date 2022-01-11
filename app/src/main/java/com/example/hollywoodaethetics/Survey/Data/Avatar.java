package com.example.hollywoodaethetics.Survey.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.FileUtils;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Avatar{
    //User avatar to-send configuration
    public MultipartBody.Part avatar;

    public Avatar(String filepath){
        if(filepath != "No") {
            File file = new File(filepath);
            Log.d("Avatar", filepath);
            avatar = MultipartBody.Part.createFormData("file", file.getName(),
                    RequestBody.create(MediaType.parse("image/*"), file));
        }
        else avatar = null;
    }
}


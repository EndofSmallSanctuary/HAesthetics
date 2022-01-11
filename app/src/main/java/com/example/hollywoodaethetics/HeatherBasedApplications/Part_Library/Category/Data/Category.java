package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Category.Data;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.Data.KeyLifts;
import com.google.android.exoplayer2.C;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Category {
   public String name;
   public String description;
   public int img;

    public Category(String name, String description, int img) {
        this.name = name;
        this.description = description;
        this.img = img;
    }

    public static List<Category> categoryList(){
        List<Category> result = new ArrayList<>();
        Category keylifts = new Category("Keylifts","The best, The strongest, The useful - that's the trully description of Keylifts, we recommend", R.drawable.keylifts);
        Category keylifts2 = new Category("Chest & Shoulders","The best, The strongest, The useful - that's the trully description of Keylifts, we recommend", R.drawable.chestandarms);
        Category keylifts3 = new Category("Arms","The best, The strongest, The Greatest - that's the trully description of Keylifts, we recommend", R.drawable.arms);
        Category keylifts4 = new Category( "Lowerbody & ABS","The best, The strongest, The useful - that's the trully description of Keylifts, we recommend", R.drawable.lowerbody);



        result.add(keylifts);
        result.add(keylifts2);

        result.add(keylifts3);
         result.add(keylifts4);
        return result;
    }
}

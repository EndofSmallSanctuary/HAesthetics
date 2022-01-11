package com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.Data.ExerciseDetailsResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.ViewModel.ExerciseDetailsViewModel;
import com.example.hollywoodaethetics.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDetailsActivity extends AppCompatActivity {

    ExerciseDetailsViewModel viewModel;
    Boolean isneedtobesaved = false;
    @BindView(R.id.exercise_guide)
    YouTubePlayerView playerView;
    @BindView(R.id.exercise_backbutton)
    ImageView backbutton;
    @BindView(R.id.exercise_weight_field)
    EditText weightfield;
    @BindView(R.id.exercise_reps_field)
    EditText repsfield;
    @BindView(R.id.exercise_img)
    ImageView img;
    @BindView(R.id.exercise_title)
    TextView title;
    @BindView(R.id.exercise_description)
    TextView desc;
    @BindView(R.id.exercise_category)
    TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);

        viewModel = new ViewModelProvider(this).get(ExerciseDetailsViewModel.class);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT );

        Glide.with(this).load(getIntent().getStringExtra("img")).into(img);
        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("desc"));
        String ctguppercase = getIntent().getStringExtra("category").substring(0,1).toUpperCase()+getIntent().getStringExtra("category").substring(1);
        category.setText("Category: " + ctguppercase);
        if(getIntent().getStringExtra("category").equals("keylift")){
            repsfield.setFocusable(false);
            repsfield.setClickable(false);
            repsfield.setText("5");
        } else fieldsinitiation(repsfield);
        fieldsinitiation(weightfield);
        youtubeinitiation();

        viewModel.getPersonalstats(getSharedPreferences(
                "UserLocals", Context.MODE_PRIVATE).getString("id",""),
                getIntent().getStringExtra("category"),
                getIntent().getStringExtra("title")).observe(this, new Observer<ExerciseDetailsResponce>() {
            @Override
            public void onChanged(ExerciseDetailsResponce exerciseDetailsResponce) {
                if((exerciseDetailsResponce.stat!=0.0f) && (exerciseDetailsResponce.reps!=0)) {
                    weightfield.setText(Float.toString(exerciseDetailsResponce.stat));
                    repsfield.setText(Integer.toString(exerciseDetailsResponce.reps));
                }
            }
        });

        backbutton.setOnClickListener(view -> {
            onBackPressed();
        });


    }

    @Override
    protected void onPause() {
        if((isneedtobesaved) && (repsfield.getText().length()!=0) || (weightfield.getText().length()!=0))
            viewModel.updatePersonalstats(getSharedPreferences(
                    "UserLocals",Context.MODE_PRIVATE).getString("id",""),
                    getIntent().getStringExtra("category"),
                    getIntent().getStringExtra("title"),
                    Float.parseFloat(weightfield.getText().toString()),
                    Integer.parseInt(repsfield.getText().toString())
            );

        super.onPause();
    }

    public void fieldsinitiation(EditText field){
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isneedtobesaved = true;
            }
        });
    }

    public void youtubeinitiation(){

        YouTubePlayerListener youTubePlayerListener = new YouTubePlayerListener() {
            @Override
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(getIntent().getStringExtra("video"),0.0f);
            }

            @Override
            public void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState playerState) {

            }

            @Override
            public void onPlaybackQualityChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackQuality playbackQuality) {

            }

            @Override
            public void onPlaybackRateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackRate playbackRate) {

            }

            @Override
            public void onError(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerError playerError) {

            }

            @Override
            public void onCurrentSecond(@NotNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoDuration(@NotNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoLoadedFraction(@NotNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoId(@NotNull YouTubePlayer youTubePlayer, @NotNull String s) {

            }

            @Override
            public void onApiChange(@NotNull YouTubePlayer youTubePlayer) {

            }
        };
        playerView.addYouTubePlayerListener(youTubePlayerListener);
        getLifecycle().addObserver(playerView);

    }
}

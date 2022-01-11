package com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.YoutubeDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Data.exInterpretationResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.ViewModel.exInterpretationViewModel;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.YoutubeDetails.Adapter.YoutubeDetailsAdapter;
import com.example.hollywoodaethetics.R;
import com.google.android.exoplayer2.ui.PlayerView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YoutubeDetailsActivity extends AppCompatActivity {

    exInterpretationViewModel viewModel;

    @BindView(R.id.video_toolbar_container)
    FrameLayout toolbarcontainer;
    @BindView(R.id.video_listed_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.video_empty_recycler)
    TextView emptyrecycler;
    @BindView(R.id.video_exercise_container)
    FrameLayout container;
    @BindView(R.id.youtube_toolbar)
    Toolbar toolbar;
    @BindView(R.id.video_listed_exercises)
    TextView listedheader;
    @BindView(R.id.video_videoname)
    TextView videoname;
    @BindView(R.id.video_player)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.video_channel_name)
    TextView channelname;
    @BindView(R.id.video_channel_img)
    CircularImageView channelimg;
    @BindView(R.id.video_channel_date)
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dogs",getIntent().getStringExtra("exids"));
         setContentView(R.layout.activity_youtube_details);
        ButterKnife.bind(this);
        viewModel = new ViewModelProvider(this).get(exInterpretationViewModel.class);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        instantinitiate();
        listedinitiation();

    }


    private void listedinitiation(){
        YoutubeDetailsAdapter adapter = new YoutubeDetailsAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

        if(!getIntent().getStringExtra("exids").isEmpty()) {
            viewModel.getYoutubeDetailsResponce(getIntent().getStringExtra("exids")).observe(this, new Observer<List<exInterpretationResponce>>() {
                @Override
                public void onChanged(List<exInterpretationResponce> exInterpretationResponces) {
                    emptyrecycler.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.setExInterpretationResponces(exInterpretationResponces);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        listedheader.setOnClickListener(view -> {
            if(container.getVisibility()== View.GONE) {
                listedheader.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_up), null);
                container.setVisibility(View.VISIBLE);
            } else {
                listedheader.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_down), null);
                container.setVisibility(View.GONE);
            }
        });
    }

    private void instantinitiate(){
        Intent intent = getIntent();
        videoname.setText(intent.getStringExtra("videoname"));
        Glide.with(this).load(intent.getStringExtra("channelimg")).into(channelimg);
        channelname.setText(intent.getStringExtra("channelname"));
        date.setText(intent.getStringExtra("date"));

        YouTubePlayerListener youTubePlayerListener = new YouTubePlayerListener() {
            @Override
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(intent.getStringExtra("video_id"),0.0f);
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
        youTubePlayerView.addYouTubePlayerListener(youTubePlayerListener);
        getLifecycle().addObserver(youTubePlayerView);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if(YoutubeDetailsActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT );
            toolbarcontainer.setVisibility(View.GONE);
            youTubePlayerView.enterFullScreen();
        }
        else {
           getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(getResources().getColor(R.color.dark,null));
            toolbarcontainer.setVisibility(View.VISIBLE);
            youTubePlayerView.exitFullScreen();
        }
        super.onConfigurationChanged(newConfig);
    }
}

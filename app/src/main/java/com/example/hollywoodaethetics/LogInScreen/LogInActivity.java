package com.example.hollywoodaethetics.LogInScreen;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.SignInScreen.SignInActivity;
import com.example.hollywoodaethetics.Survey.SurveyActivity;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity {

    @BindView(R.id.button_CreateProfile)
    Button createbutton;
    @BindView(R.id.login_SingIn)
    TextView signin;
    SimpleExoPlayer player;
    PlayerView playerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        player = new SimpleExoPlayer.Builder(this).build();
        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        player.setPlayWhenReady(true);
        playerView = findViewById(R.id.VideoPrev);
        playerView.hideController();
        playerView.setUseController(false);
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        playerView.setPlayer(player);
        Uri uri = Uri.parse("asset:///test.mp4");
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(this,"Exoplayer");
        ProgressiveMediaSource videosource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(uri);

        player.prepare(videosource);

        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, SurveyActivity.class));
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, SignInActivity.class));
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT );
        player.seekTo(0);
        player.setPlayWhenReady(true);
        super.onResume();
    }

    @Override
    protected void onPause() {
        player.setPlayWhenReady(false);
        super.onPause();
    }
}

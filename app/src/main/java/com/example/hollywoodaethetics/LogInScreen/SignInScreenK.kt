package com.example.hollywoodaethetics.LogInScreen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.hollywoodaethetics.R
import com.example.hollywoodaethetics.Survey.FragmentsK.SurveyActivityK
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_log_in.*

class SignInScreenK : AppCompatActivity() {

    private lateinit var player : SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        window.setFlags( WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

       player = SimpleExoPlayer.Builder(this).build();

        var uri =   Uri.parse("asset:///test.mp4");


        var datasource = DefaultDataSourceFactory(this,"Exoplayer");
        var videosource = ProgressiveMediaSource.Factory(datasource).createMediaSource(uri)
        var button = findViewById<Button>(R.id.button_CreateProfile)
        button.setOnClickListener { startActivity(Intent(this,
            SurveyActivityK::class.java)) }


        player.repeatMode = Player.REPEAT_MODE_ALL
        player.playWhenReady = true
        player.prepare(videosource)





        VideoPrev.hideController()
        VideoPrev.useController = false;
        VideoPrev.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL



        VideoPrev.player = player
    }


    override fun onPause() {

        player.setPlayWhenReady(false)
        player.getPlaybackState()

        super.onPause()


    }

    override fun onResume() {
        player.setPlayWhenReady(true)
        player.getPlaybackState()


        super.onResume()
    }
}

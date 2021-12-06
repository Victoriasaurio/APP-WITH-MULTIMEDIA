package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imagesvg;

    private ObjectAnimator animatorRotation;

    private long animationDuration = 7000;

    private AnimatorSet animatorSet;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animatorSet = new AnimatorSet();

        imagesvg = findViewById(R.id.imagesvg);
    }

    public void play(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.ula_ula);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        animacion();
        player.start();
    }

    public void pause(View v) {
        if(player != null) {
            player.pause();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if(player!=null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    public void video(View view) {
        Intent intent = new Intent(this, video.class);
        startActivity(intent);
    }

    public void animacion() {
        animatorRotation = ObjectAnimator.ofFloat(imagesvg, "rotation",0f, 360f);
        animatorRotation.setDuration(animationDuration);
        AnimatorSet animatorSetRotator = new AnimatorSet();
        animatorSetRotator.playTogether(animatorRotation);
        //animatorSetRotator.start();
        animatorSetRotator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //super.onAnimationEnd(animation);
                animation.start();
            }
        });
        animatorSetRotator.start();
    }

    public void audio(View view) {
        Intent intent = new Intent(this, Audio.class);
        startActivity(intent);
    }

    public void  goToTemperature(View view) {
        Intent intent = new Intent(this, Temperature.class);
        startActivity(intent);
    }
}
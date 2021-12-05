package com.example.mediaplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class Audio extends AppCompatActivity {
    private ImageButton StartRecording, StopRecording, StartPlaying;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String AudioSavaPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StartRecording = findViewById(R.id.startRecord);
        StopRecording = findViewById(R.id.stopRecord);
        StartPlaying = findViewById(R.id.startPlaying);

        StartRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermissions() == true) {

                    AudioSavaPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            +"/"+"recordingAudio.mp3";

                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    mediaRecorder.setOutputFile(AudioSavaPath);

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                        Toast.makeText(Audio.this, "Recording started", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }else {

                    ActivityCompat.requestPermissions(Audio.this,new String[]{
                            Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },1);
                }
            }
        });

        StopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaRecorder.stop();
                mediaRecorder.release();
                Toast.makeText(Audio.this, "Recording stopped", Toast.LENGTH_SHORT).show();
            }
        });

        StartPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavaPath);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(Audio.this, "Start playing", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private boolean checkPermissions() {
        int first = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return first == PackageManager.PERMISSION_GRANTED &&
                second == PackageManager.PERMISSION_GRANTED;
    }
}

package com.example.mediaplayer;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class video extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout layin = findViewById(R.id.layo);
        VideoView video = new VideoView(this);
        String origen ="android.resource://"+getPackageName()+"/"+R.raw.sea;
        Uri uri = Uri.parse(origen);
        video.setVideoURI(uri);
        LinearLayout contenedor = new LinearLayout(this);
        MediaController controlador = new MediaController(this);
        video.setMediaController(controlador);
        controlador.setAnchorView(video);
        contenedor.addView(video);
        layin.addView(contenedor);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

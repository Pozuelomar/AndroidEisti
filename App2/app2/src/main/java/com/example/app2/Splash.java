package com.example.app2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent objIndent = new Intent(this, PlayAudio.class);
        startService(objIndent);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() < time + 1000L) { }



        finish();
    }
}

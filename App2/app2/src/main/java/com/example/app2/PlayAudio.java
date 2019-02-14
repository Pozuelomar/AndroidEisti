package com.example.app2;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class PlayAudio extends Service{
    private static final String LOGCAT = null;
    MediaPlayer objPlayer;

    public void onCreate(){
        super.onCreate();
        objPlayer = MediaPlayer.create(this,R.raw.d3);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        objPlayer.start();
        if(objPlayer.isLooping() != true){
            Log.d(LOGCAT, "Problem in Playing Audio");
        }
        return 0;
    }

    public void onStop(){
        objPlayer.stop();
        objPlayer.release();
    }

    public void onPause(){
        objPlayer.stop();
        objPlayer.release();
    }
    public void onDestroy(){
        objPlayer.stop();
        objPlayer.release();
    }
    @Override
    public IBinder onBind(Intent objIndent) {
        return null;
    }
}
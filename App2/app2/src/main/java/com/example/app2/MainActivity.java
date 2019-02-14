package com.example.app2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int colorData = Color.BLACK;
    String colorString = "Pick a color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setColor();
    }

    void onGoogleClick(View view){
        String url = "http://learn.atilla.org";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    void onGalleryClick(View view){
        Intent i = new
                Intent(Intent.ACTION_VIEW,android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivity(i);

    }
    void playAudio(View view) {
        Intent objIndent = new Intent(this, PlayAudio.class);
        startService(objIndent);
    }
    void stopAudio(View view) {
        Intent objIndent = new Intent(this, PlayAudio.class);
        stopService(objIndent);
    }


    @Override
    public void onSaveInstanceState(Bundle b)
    {
        b.putInt("colorData", colorData);
        b.putString("colorString", colorString);
        super.onSaveInstanceState(b);
    }

    @Override
    public void onRestoreInstanceState(Bundle b)
    {
        super.onRestoreInstanceState(b);
        colorData = b.getInt("colorData");
        colorString = b.getString("colorString");
        setColor();
    }

    public void onColorPickerClick(View v)
    {
        Intent i = new Intent(this, SecondActivity.class);
        startActivityForResult(i, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 0) {
            colorData = Color.parseColor(data.getStringExtra("result"));
            colorString = data.getStringExtra("resultstr");
            setColor();
        }
    }

    void setColor() {
        TextView txtview = (TextView) findViewById(R.id.colortext);
        txtview.setText(colorString);
        txtview.setTextColor(colorData);
    }
}

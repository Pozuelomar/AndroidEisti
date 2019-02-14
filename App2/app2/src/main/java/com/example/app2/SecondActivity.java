package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        Intent i = getIntent();
    }

    public void colorPicked(View v)
    {
        Intent returnIntent = new Intent();
        String color = "#000000";
        String colorstr = "";

        if (((RadioButton)findViewById(R.id.red)).isChecked())
        {
            color = "#ff0000";
            colorstr = "RED";
        }
        else if (((RadioButton)findViewById(R.id.green)).isChecked())
        {
            color = "#00ff00";
            colorstr = "GREEN";
        }
        else if (((RadioButton)findViewById(R.id.blue)).isChecked())
        {
            color = "#0000ff";
            colorstr = "BLUE";
        }

        returnIntent.putExtra("result", color);
        returnIntent.putExtra("resultstr", colorstr);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
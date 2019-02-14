package com.example.app1bis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView value = findViewById(R.id.value);
        value.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (getCurrentFocus() == value) {convert();}
            }
        });

        final TextView converted = findViewById(R.id.converted);
        converted.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (getCurrentFocus() == converted) {revert_convert();}
            }
        });



        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Euro");
        categories.add("Dollard");
        categories.add("Franc suisse");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        Spinner spinner_value = (Spinner) findViewById(R.id.spinner_value);
        Spinner spinner_converted = (Spinner) findViewById(R.id.spinner_converted);
        spinner_value.setAdapter(dataAdapter);
        spinner_converted.setAdapter(dataAdapter);

        spinner_value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                revert_convert();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        spinner_converted.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                convert();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public double coef() {
        double eur = 1.0;
        double usd = 1.12;
        double chf = 1.14;

        double coef = 1.0;

        switch (((Spinner)findViewById(R.id.spinner_value)).getSelectedItem().toString()) {
            case "Euro":
                coef = coef/eur;
                break;
            case "Dollard":
                coef = coef/usd;
                break;
            case "Franc suisse":
                coef = coef/chf;
                break;
        }
        switch (((Spinner)findViewById(R.id.spinner_converted)).getSelectedItem().toString()) {
            case "Euro":
                coef = coef*eur;
                break;
            case "Dollard":
                coef = coef*usd;
                break;
            case "Franc suisse":
                coef = coef*chf;
                break;
        }
        return coef;
    }


    public void convert(View view) {
        convert();
    }
    public void convert() {
        double v;
        try {
            v = Double.parseDouble(((TextView) findViewById(R.id.value)).getText().toString().replace(',', '.'));
        } catch (NumberFormatException nfe) {
            v = 0;
        }
        ((TextView)findViewById(R.id.converted)).setText(String.format("%.2f", (Double)(v*coef())));
    }

    public void revert_convert(View view) {
        revert_convert();
    }
    public void revert_convert() {
        double v;
        try {
            v = Double.parseDouble(((TextView) findViewById(R.id.converted)).getText().toString().replace(',', '.'));
        } catch (NumberFormatException nfe) {
            v = 0;
        }
        ((TextView)findViewById(R.id.value)).setText(String.format("%.2f", (Double)(v/coef())));
    }
}

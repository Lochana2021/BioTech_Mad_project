package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class single_student extends AppCompatActivity {

    EditText txtDate, txtWeather, txtPlace, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student);
    }
}
package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.biotechgeneral.R;

public class sigle_type extends AppCompatActivity {

    TextView txtTopicType;
    TextView etn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigle_type);

        txtTopicType = findViewById(R.id.txtTopicType);
        etn = findViewById(R.id.textViewWeek);

        Intent intent = getIntent();
        String topicName = intent.getStringExtra("TYPE_01");
        //int week = intent.getIntExtra("WEEK",0);

        txtTopicType.setText(topicName);
        //etn.setText(week);
    }
}

package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ForumResponse extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_response);
    }

    public void tenp(View view) {
        EditText e = (EditText) findViewById(R.id.txt_ResponseForum);


        double response = Double.parseDouble(e.getText().toString());

        double res = (response / 25.0f) * 100;

        Toast.makeText(getApplicationContext(), "" +res, Toast.LENGTH_SHORT).show();

    }
}
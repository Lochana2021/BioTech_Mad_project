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

        EditText editText = (EditText) findViewById(R.id.StudentCount);


        double response = Double.parseDouble(e.getText().toString());
        double student = Double.parseDouble(editText.getText().toString());

        double res = (response / student) * 100;

        Toast.makeText(getApplicationContext(), "Response Percentage" +res, Toast.LENGTH_SHORT).show();

    }
}
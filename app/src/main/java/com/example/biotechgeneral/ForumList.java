package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import static com.example.biotechgeneral.R.id.img_CreateForum;

public class ForumList extends AppCompatActivity {

    ListView ForumListView;
    ArrayList<String> forumArrayList = new ArrayList<>();
    DatabaseReference dbRef;
    ArrayAdapter<String> forumArrayAdapter;
    private String[] file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_list);


    }


    public void onClick(View v) {

        Intent i = new  Intent(getApplicationContext(),CreateForum.class);
        startActivity(i);
    }


}
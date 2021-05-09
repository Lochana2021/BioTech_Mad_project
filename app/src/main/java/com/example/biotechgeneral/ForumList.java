package com.example.biotechgeneral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class  ForumList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_list);
    }
// onClick function to create forum
    public void onClick(View view) {
        Intent i = new Intent(this, CreateForum.class);
        startActivity(i);
    }

// Cancel button
    public void ViewForums(View view) {
        Intent i = new Intent (this,ForumView.class);
        startActivity(i);
    }
}
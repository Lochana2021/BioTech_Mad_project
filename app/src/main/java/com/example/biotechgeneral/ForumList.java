package com.example.biotechgeneral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class  ForumList extends AppCompatActivity {

ListView forumListView;
ArrayList<String> forumArrayList = new ArrayList<>();
DatabaseReference dbRef;

ArrayAdapter<String> forumArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_list);

        Intent getIntentMainActivity = getIntent();

        forumListView = (ListView) findViewById(R.id.ListView_Forum);


        // Array Adapter

        forumArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,forumArrayList);

        forumListView.setAdapter(forumArrayAdapter);
        dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.child("ForumClass").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String forumMapped = snapshot.child("ForumName").getValue(String.class);
                forumArrayList.add(forumMapped);
                forumArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                forumArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    public void onClick(View v) {

        Intent i = new  Intent(getApplicationContext(),CreateForum.class);
        startActivity(i);
    }


}
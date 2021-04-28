package com.example.biotechgeneral;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ForumView extends AppCompatActivity {

    List<Forum> forum;
    RecyclerView recyclerView;
    ForumAdapter forumAdapter;
    DatabaseReference F_dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_view);
        recyclerView = findViewById(R.id.Forum_ListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        forum = new ArrayList<>();

        F_dbRef = FirebaseDatabase.getInstance().getReference("Forum");
        F_dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    Forum data = ds.getValue(Forum.class);
                    forum.add(data);
                }
            forumAdapter = new ForumAdapter(forum);
                recyclerView.setAdapter(forumAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
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

public class ForumDisplay extends AppCompatActivity {

    List<ForumStudent>forumStudentList;
    RecyclerView recyclerView;
    ForumStudentAdapter forumStudentAdapter;
    DatabaseReference F_dbRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_display);
        recyclerView = findViewById(R.id.display_RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        forumStudentList = new ArrayList<>();

        F_dbRef = FirebaseDatabase.getInstance().getReference("ForumStudent");
        F_dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    ForumStudent data = ds.getValue(ForumStudent.class);
                    forumStudentList.add(data);
                }
                forumStudentAdapter =  new ForumStudentAdapter(forumStudentList);
                recyclerView.setAdapter(forumStudentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });















    }







}
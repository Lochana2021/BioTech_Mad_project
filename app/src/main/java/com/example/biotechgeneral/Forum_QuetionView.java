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

public class Forum_QuetionView extends AppCompatActivity {
    List<ForumQuetions> forumQuetionsList;
    RecyclerView recyclerView;

    ForumQDisplayAdapter forumQDisplayAdapter;
    DatabaseReference F_dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum__quetion_view);
        recyclerView = findViewById(R.id.QDisplay_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        forumQuetionsList = new ArrayList<>();

        F_dbRef = FirebaseDatabase.getInstance().getReference("ForumQuetions");
        F_dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    ForumQuetions data = ds.getValue(ForumQuetions.class);
                    forumQuetionsList.add(data);
                }
                forumQDisplayAdapter = new ForumQDisplayAdapter(forumQuetionsList);
                recyclerView.setAdapter(forumQDisplayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
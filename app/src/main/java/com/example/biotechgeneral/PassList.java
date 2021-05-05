package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PassList extends AppCompatActivity {


    ListView passListView;
    ArrayList<String> passArrayList = new ArrayList<>();
    DatabaseReference dbRef;
    ArrayAdapter<String> passArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_list);

        Intent getIntentMainActivity = getIntent();

        // Associate the quiz list with the corresponding ListView
        passListView = (ListView) findViewById(R.id.idPassListView);

        // Set the ArrayAdapter to the ListView
        passArrayAdapter = new ArrayAdapter<String>(this,R.layout.passlist_item_1,passArrayList);
        passListView.setAdapter(passArrayAdapter);

        // Associate the quiz Firebase Database Reference with the database's quiz object
        dbRef = FirebaseDatabase.getInstance().getReference();

        // Attach a ChildEventListener to the quiz database, so we can retrieve the quiz entries
        dbRef.child("QuizClass").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                // Get the value from the DataSnapshot and add it to the quiz' list
                String quizMapped = snapshot.child("quizNo").getValue(String.class);
                passArrayList.add("Quiz "+quizMapped);
                // quizArrayList.add(quizMapped);

                // Notify the ArrayAdapter that there was a change
                passArrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                passArrayAdapter.notifyDataSetChanged();
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
        } );

    }
}
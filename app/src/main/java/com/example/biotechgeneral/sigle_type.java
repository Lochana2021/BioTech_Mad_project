package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.biotechgeneral.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class sigle_type extends AppCompatActivity {

    TextView txtTopicType;
    TextView etn;

    ListView stdAssListView;
    ArrayList<String> assStdArrayList = new ArrayList<>();
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigle_type);

        /*intent passing*/
        txtTopicType = findViewById(R.id.txtTopicType);
        etn = findViewById(R.id.textViewWeek);

        Intent intent = getIntent();
        String topicName = intent.getStringExtra("TYPE_01");
        //int week = intent.getIntExtra("WEEK",0);

        txtTopicType.setText(topicName);
        //etn.setText(week);

        /*adapter retrieve listView*/
        //ArrayAdapter<String> assStdArrayAdapter = new ArrayAdapter<String>(sigle_type.this.android.R.layout.simple_list_item_1,assStdArrayList);
        stdAssListView = (ListView) findViewById(R.id.stdListAss);
        ArrayAdapter<String> assStdArrayAdapter = new ArrayAdapter<String>(this,R.layout.liststdass_item,assStdArrayList);


        stdAssListView.setAdapter(assStdArrayAdapter);

        //dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment");
        dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String assMapped = snapshot.getValue(String.class);
                assStdArrayList.add(assMapped);
                assStdArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                assStdArrayAdapter.notifyDataSetChanged();
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
}

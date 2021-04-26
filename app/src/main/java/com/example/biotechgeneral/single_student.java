package com.example.biotechgeneral;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class single_student extends AppCompatActivity {

    Assignment ass;
    DatabaseReference dbRef;
    TextView txtStdID, txtDate, txtWeather, txtPlace, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student);

        ass = new Assignment();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment").child("ASS2");

        /*assigning variables*/
        txtStdID = findViewById(R.id.StdNameView);
        txtDate = findViewById(R.id.TechDateView);
        txtWeather = findViewById(R.id.TechWeatherView);
        txtPlace = findViewById(R.id.TechPlaceView);
        txtDescription = findViewById(R.id.TechDescriptionView);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    txtStdID.setText(snapshot.child("stdAssID").getValue().toString());
                    txtDate.setText(snapshot.child("date").getValue().toString());
                    txtWeather.setText(snapshot.child("weather").getValue().toString());
                    txtPlace.setText(snapshot.child("place").getValue().toString());
                    txtDescription.setText(snapshot.child("description").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class std_profile extends AppCompatActivity {

    TextView txtName, txtEmail;
    private String email, password;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;
    //private static final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_profile);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        txtName = findViewById(R.id.userNameStd);
        txtEmail = findViewById(R.id.userEmailStd);

        //database = FirebaseDatabase.getInstance();
        //dbRef = database.getReference(USERS);

        /*dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)){
                        //txtName.setText(ds.child("fullName").getValue(String.class));
                        txtEmail.setText(email);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}
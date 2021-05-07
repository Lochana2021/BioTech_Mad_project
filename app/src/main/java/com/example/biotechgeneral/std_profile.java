package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseUser user;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_profile);

        //Intent intent = getIntent();
        //email = intent.getStringExtra("email");

        user = FirebaseAuth.getInstance().getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        txtName = findViewById(R.id.userNameStd);
        txtEmail = findViewById(R.id.userEmailStd);

        dbRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student std = snapshot.getValue(Student.class);

                if(std != null){
                    txtName.setText(snapshot.child("full_name").getValue().toString());
                    txtEmail.setText(snapshot.child("email").getValue().toString());
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
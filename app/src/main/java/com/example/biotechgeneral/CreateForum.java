package com.example.biotechgeneral;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateForum extends AppCompatActivity {


ForumClass   forum;
DatabaseReference dbRef;
    Button button01, button02;
    EditText ForumName, description, ForumType;
    long forumID;

    public void clearUserInputs(){
        ForumName.setText("");
        description.setText("");
        ForumType.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forum);
        Intent getIntentFList = getIntent();
        forum = new ForumClass();
        dbRef = FirebaseDatabase.getInstance().getReference().child("ForumClass");

        button01 = findViewById(R.id.btn_forumcreate);
        button02 = findViewById(R.id.btn_FcreateCancel);
        ForumName = findViewById(R.id.txt_Fname);
        description = findViewById(R.id.txt_description);
        ForumType = findViewById(R.id.txt_Ftype);


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    forumID = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // cancel button
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ForumList.class);
                startActivity(i);
            }
        });


        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (TextUtils.isEmpty(ForumName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter forum name.", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(description.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter forum description.", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(ForumType.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter forum type", Toast.LENGTH_LONG).show();

                    else{
                        forum.setForumName(ForumName.getText().toString().trim());
                        forum.setDescription(description.getText().toString().trim());
                        forum.getForumType(ForumType.getText().toString().trim());
                    }

                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }

            }
        });









    }

}
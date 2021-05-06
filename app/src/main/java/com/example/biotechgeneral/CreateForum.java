package com.example.biotechgeneral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateForum extends AppCompatActivity {

    EditText fName, description, fType;
    Button Create;
    DatabaseReference F_dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forum);


        fName = findViewById(R.id.txt_Fname);
        description = findViewById(R.id.txt_description);
        fType = findViewById(R.id.txt_Ftype);
        Create = findViewById(R.id.btn_forumcreate);

        F_dbRef = FirebaseDatabase.getInstance().getReference().child("Forum");
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertForum();

            }
        });

    }

    private void insertForum(){
        String Name = fName.getText().toString();
        String Description= description.getText().toString();
        String Type = fType.getText().toString();

        Forum forum = new Forum(Name, Description,Type);

        F_dbRef.push().setValue(forum);
        Toast.makeText(CreateForum.this,"data  inserted",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Forum_Dashboard.class);
        startActivity(i);



    }

    public void onCancel(View view) {
        Intent i = new Intent(this,ForumList.class);
        startActivity(i);
    }
}
package com.example.biotechgeneral;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuetion extends AppCompatActivity {

    EditText fname, quetion, quetionNo;
    Button submit;
    DatabaseReference F_dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quetion);

        fname = findViewById(R.id.txt_FName);
        quetion = findViewById(R.id.F_Addquetion);
        submit = findViewById(R.id.btn_FsubmitQ);
        quetionNo = findViewById(R.id.txt_quetionNo);

        F_dbRef = FirebaseDatabase.getInstance().getReference().child("ForumStudent");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertQuetion();
            }
        });
    }
   private void insertQuetion(){
        String FName = fname.getText().toString();
        String Quetion = quetion.getText().toString();
       String QuetionNo = quetionNo.getText().toString();
        ForumStudent forumStudent = new ForumStudent(FName, Quetion, QuetionNo);


        F_dbRef.push().setValue(forumStudent);
        Toast.makeText(AddQuetion.this,"Quetion added",Toast.LENGTH_SHORT).show();
    }
}
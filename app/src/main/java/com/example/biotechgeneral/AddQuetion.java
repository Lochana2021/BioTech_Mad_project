package com.example.biotechgeneral;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuetion extends AppCompatActivity {

    EditText quetion;
    Button submit;

    DatabaseReference F_dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quetion);

        quetion = findViewById(R.id.F_Addquetion);
        submit = findViewById(R.id.btn_FsubmitQ);

        F_dbRef = FirebaseDatabase.getInstance().getReference().child("Forum");

      /*  submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertQuetion();
            }
        });*/
    }
   /* private void insertQuetion(){
        String Quetion = quetion.getText().toString();
        Forum forum = new Forum(Quetion);
        F_dbRef.push().setValue(forum);
        Toast.makeText(AddQuetion.this,"Quetion added",Toast.LENGTH_SHORT).show();
    }*/
}
package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login_bio extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btnLogIn;
    DatabaseReference dbRef;
    FirebaseAuth fAuth;

    String emailLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bio);

        txtEmail = findViewById(R.id.loginEmail);
        txtPassword = findViewById(R.id.loginPassword);

        btnLogIn = findViewById(R.id.btnLogIn);
        fAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("STD1");
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                //validate did not do...
                //authenticate the user...
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_bio.this,"Logged in Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),navigation.class));
                        }
                        else {
                            Toast.makeText(login_bio.this,"Logged in Unsuccessful",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    TextView txtCreateAcc;

    String emailLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bio);

        txtCreateAcc = findViewById(R.id.stdCreateAcc);

        txtEmail = findViewById(R.id.loginEmail);
        txtPassword = findViewById(R.id.loginPassword);

        btnLogIn = findViewById(R.id.btnLogIn);
        fAuth = FirebaseAuth.getInstance();

        txtCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("STD1");
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                //validate
                if(TextUtils.isEmpty(email)){
                    txtEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    txtPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6){
                    txtPassword.setError("Password must be >= 6 characters");
                    return;
                }
                //authenticate the user...
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_bio.this,"Logged in Successfully",Toast.LENGTH_LONG).show();

                            //create intent
                            final Intent intent = new Intent(getApplicationContext(),std_profile.class);
                            String userEmail = email;

                            intent.putExtra("email",userEmail);

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
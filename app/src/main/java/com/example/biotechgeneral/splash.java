package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*Objects.requireNonNull(getSupportActionBar()).hide();
        final Intent i = new Intent(splash.this, TeacherDashboard.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 2000);*/

        final int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash.this,StartingDashboard.class);
                splash.this.startActivity(i);
                splash.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);

        //startActivity(new Intent(this,login_bio.class));
}
}
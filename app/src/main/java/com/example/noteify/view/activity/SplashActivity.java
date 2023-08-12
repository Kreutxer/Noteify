package com.example.noteify.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.noteify.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean userLoggedIn = checkUserLoggedIn();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userLoggedIn) {
                    // User is already logged in, navigate to the appropriate activity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class); // Change to the appropriate activity
                    startActivity(intent);
                } else {
                    // User is not logged in, navigate to LoginActivity
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 2000);
    }
    private boolean checkUserLoggedIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        return user != null;
    }

}
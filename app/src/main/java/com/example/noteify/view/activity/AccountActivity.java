package com.example.noteify.view.activity;

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.noteify.R;
import com.example.noteify.view.activity.account.DetailApp;
import com.example.noteify.view.activity.account.DetailCreator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

// ... (imports and other code)

public class AccountActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Get the current user from Google authentication
        FirebaseUser googleUser = FirebaseAuth.getInstance().getCurrentUser();

        // Replace this with the custom login email you have obtained
        String customUserEmail = "customuser@example.com";

        reference = FirebaseDatabase.getInstance().getReference("Users");
        auth = FirebaseAuth.getInstance();

        ImageButton add = findViewById(R.id.btn_cross);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        final ImageView image = findViewById(R.id.image_profile);
        final TextView username = findViewById(R.id.account_name);
        final TextView email = findViewById(R.id.text_email);

        // Load Google user profile photo
        if (googleUser != null) {
            Glide.with(this).load(googleUser.getPhotoUrl()).circleCrop().into(image);

            reference.child(googleUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users users = snapshot.getValue(Users.class);

                    if (users != null) {
                        String fullname = users.name;
                        String googleEmail = googleUser.getEmail();
                        username.setText(fullname);
                        email.setText(googleEmail);  // Set the Google-authenticated email
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AccountActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Set the email from the custom login
            username.setText("Custom User");
            email.setText(customUserEmail);
        }

        TextView btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        TextView btn_app = findViewById(R.id.btn_app);
        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, DetailApp.class);
                startActivity(intent);
            }
        });

        TextView btn_creator = findViewById(R.id.btn_creator);
        btn_creator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, DetailCreator.class);
                startActivity(intent);
            }
        });
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        // Navigate back to the login or main activity
        // For example, if you have a LoginActivity:
        Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */

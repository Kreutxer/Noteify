package com.example.noteify.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteify.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {

    private DatabaseReference reference;

//    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();

//        final TextView username = findViewById(R.id.text_email);
        final ImageView image = (ImageView) findViewById(R.id.image_profile);
        final TextView username = (TextView) findViewById(R.id.account_name);
        final TextView email = (TextView) findViewById(R.id.text_email);



       reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);


                if (users != null) {
                    String fullname = users.name;
                    String emailtext = users.email;

                    username.setText(fullname);
                    email.setText(emailtext);
                }
            }


           @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AccountActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
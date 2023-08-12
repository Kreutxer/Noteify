package com.example.noteify.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteify.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText etEmail;
    private EditText etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.username); // Make sure you have the correct IDs in your layout
        etPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btn_regis);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validate user input (you can add more validation if needed)

                // Register user using Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegistrationActivity.this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                // User registration successful, you can navigate to another activity
                                // For example, after successful registration:
                                 startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                 finish();
                            } else {
                                // Handle registration failure
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    // Handle weak password
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    // Handle invalid email
                                } catch (FirebaseAuthUserCollisionException e) {
                                    // Handle user collision (user already exists)
                                } catch (Exception e) {
                                    // Handle other errors
                                }

                                // Show an error message to the user
                                Toast.makeText(RegistrationActivity.this, "Registration failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}

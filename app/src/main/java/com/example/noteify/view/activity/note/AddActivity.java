package com.example.noteify.view.activity.note;

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.noteify.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    private DatabaseReference noteRef;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        noteRef = FirebaseDatabase.getInstance().getReference("notes");

        ImageButton back = findViewById(R.id.back);
        ImageButton save = findViewById(R.id.save);
        EditText title = findViewById(R.id.title);
        EditText desc = findViewById(R.id.desc);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNoteToDatabase(title.getText().toString(), desc.getText().toString());
            }
        });

        // Handle other UI interactions as needed
    }

    private void saveNoteToDatabase(String noteTitle, String noteDescription) {
        if (currentUser != null) {
            String userId = currentUser.getUid();
            String noteId = noteRef.push().getKey();

            Note note = new Note();
            note.setTitle(noteTitle);
            note.setDescription(noteDescription);
            note.setUserId(userId);

            // You can also set other properties of the Note object as needed

            noteRef.child(userId).child(noteId).setValue(note)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(AddActivity.this, "Note saved successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Return to previous activity after saving
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AddActivity.this, "Failed to save note", Toast.LENGTH_SHORT).show();
                    });
        }
    }

}

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */
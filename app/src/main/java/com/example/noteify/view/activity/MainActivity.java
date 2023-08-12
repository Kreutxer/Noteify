package com.example.noteify.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.noteify.R;
import com.example.noteify.view.activity.note.AddActivity;
import com.example.noteify.view.activity.note.DetailNote;
import com.example.noteify.view.activity.note.Note;
import com.example.noteify.view.activity.note.NoteAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnItemClickListener{

    private NoteAdapter noteAdapter;
    private DatabaseReference noteRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteAdapter = new NoteAdapter(new ArrayList<>());
        noteAdapter.setOnItemClickListener(this);

        LinearLayout add = findViewById(R.id.add_component);
        ImageButton pencil = findViewById(R.id.pencil);
        ImageButton mic = findViewById(R.id.microphone);
        ImageButton dots = findViewById(R.id.dot_menu);


        dots.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            startActivity(intent);
        });

        mic.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SpeechActivity.class);
            startActivity(intent);
        });

        pencil.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DrawActivity.class);
            startActivity(intent);
        });

        add.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        if (getIntent().hasExtra("selectedNote")) {
            Note note = getIntent().getParcelableExtra("selectedNote");
            if (note != null) {
                Log.d("DetailNoteActivity", "Received note: " + note.getTitle());
                // Use the note's data to populate your UI elements
            }
        }

        initRecyclerView();
        initFirebase();
        loadNotes();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(new ArrayList<>());
        recyclerView.setAdapter(noteAdapter);
    }

    private void initFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            noteRef = database.getReference("notes").child(user.getUid());
        }
    }

    private void loadNotes() {
        if (noteRef != null) {
            noteRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Note> notes = new ArrayList<>();
                    for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                        Note note = noteSnapshot.getValue(Note.class);
                        notes.add(note);
                    }
                    noteAdapter.setNoteList(notes); // Update the note list here
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error
                }
            });
        }
    }

    @Override
    public void onItemClick(Note note) {
        Log.d("MainActivity", "Item clicked: " + note.getTitle());
        Intent intent = new Intent(this, DetailNote.class);
        intent.putExtra("selectedNote", note); // Pass the clicked note to DetailNoteActivity
        startActivity(intent);
    }
}

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */

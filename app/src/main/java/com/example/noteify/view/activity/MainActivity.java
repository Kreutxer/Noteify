package com.example.noteify.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.noteify.R;
import com.example.noteify.view.activity.AccountActivity;
import com.example.noteify.view.activity.DrawActivity;
import com.example.noteify.view.activity.SpeechActivity;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private DatabaseReference noteRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout add = findViewById(R.id.add_component);
        ImageButton pencil = findViewById(R.id.pencil);
        ImageButton mic = findViewById(R.id.microphone);
        ImageButton dots = findViewById(R.id.dot_menu);


        dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpeechActivity.class);
                startActivity(intent);
            }
        });

        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        initRecyclerView();
        initFirebase();
        loadNotes();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.rec_view);
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

}

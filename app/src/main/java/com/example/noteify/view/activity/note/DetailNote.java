package com.example.noteify.view.activity.note;

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.noteify.R;

public class DetailNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);

        if (getIntent().hasExtra("selectedNote")) {
            Note note = getIntent().getParcelableExtra("selectedNote");
            if (note != null) {
                // Use the note's data to populate your UI elements
            }
        }
    }
}

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */
package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class CatatanHarianActivity extends AppCompatActivity {

    private EditText etNoteTitle, etNoteContent;
    private Button btnSaveNote;
    private ListView lvNotes;
    private ArrayList<String> notesList;
    private ArrayAdapter<String> notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan_harian);

        // Initialize views
        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
        btnSaveNote = findViewById(R.id.btnSaveNote);
        lvNotes = findViewById(R.id.lvNotes);

        // Initialize notes list and adapter
        notesList = new ArrayList<>();
        notesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, notesList);
        lvNotes.setAdapter(notesAdapter);

        // Set click listener for save button
        btnSaveNote.setOnClickListener(view -> saveNote());

        // Set click listener for list items to view note details
        lvNotes.setOnItemClickListener((parent, view, position, id) -> {
            String selectedNote = notesList.get(position);
            Toast.makeText(CatatanHarianActivity.this,
                    "Selected note: " + selectedNote, Toast.LENGTH_LONG).show();
            // In a real app, you would display note details or allow editing
        });
    }

    private void saveNote() {
        String title = etNoteTitle.getText().toString();
        String content = etNoteContent.getText().toString();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please enter both title and content",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Format current date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        // Add note to list
        String noteEntry = currentDate + " - " + title;

        notesList.add(0, noteEntry); // Add to top of list
        notesAdapter.notifyDataSetChanged();

        // Clear input fields
        etNoteTitle.setText("");
        etNoteContent.setText("");

        Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show();
    }
}
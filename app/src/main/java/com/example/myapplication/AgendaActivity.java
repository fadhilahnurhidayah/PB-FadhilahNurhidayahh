package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class AgendaActivity extends AppCompatActivity {

    private EditText etEventTitle, etEventDate, etEventTime, etEventLocation, etEventDescription;
    private Button btnSaveEvent;
    private ListView lvEvents;
    private ArrayList<String> eventsList;
    private ArrayAdapter<String> eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        // Initialize views
        etEventTitle = findViewById(R.id.etEventTitle);
        etEventDate = findViewById(R.id.etEventDate);
        etEventTime = findViewById(R.id.etEventTime);
        etEventLocation = findViewById(R.id.etEventLocation);
        etEventDescription = findViewById(R.id.etEventDescription);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);
        lvEvents = findViewById(R.id.lvEvents);

        // Initialize events list and adapter
        eventsList = new ArrayList<>();
        eventsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, eventsList);
        lvEvents.setAdapter(eventsAdapter);

        // Set click listener for save button
        btnSaveEvent.setOnClickListener(view -> saveEvent());

        // Set click listener for list items
        lvEvents.setOnItemClickListener((parent, view, position, id) -> {
            String selectedEvent = eventsList.get(position);
            Toast.makeText(AgendaActivity.this,
                    "Selected event: " + selectedEvent, Toast.LENGTH_LONG).show();
            // For a real app, you would show event details
        });
    }

    private void saveEvent() {
        String title = etEventTitle.getText().toString();
        String date = etEventDate.getText().toString();
        String time = etEventTime.getText().toString();
        String location = etEventLocation.getText().toString();
        String description = etEventDescription.getText().toString();

        if (title.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please enter at least title, date and time",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Add event to list
        String eventEntry = date + " " + time + " - " + title + " at " + location;

        eventsList.add(0, eventEntry); // Add to top of list
        eventsAdapter.notifyDataSetChanged();

        // Clear input fields
        etEventTitle.setText("");
        etEventDate.setText("");
        etEventTime.setText("");
        etEventLocation.setText("");
        etEventDescription.setText("");

        Toast.makeText(this, "Event saved successfully", Toast.LENGTH_SHORT).show();
    }
}
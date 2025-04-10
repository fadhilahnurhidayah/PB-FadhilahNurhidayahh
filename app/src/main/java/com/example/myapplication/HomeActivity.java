package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private CardView budgetTrackerCard, catatanHarianCard, todoListCard, agendaCard;
    private TextView tvGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize greeting with user name (if available)
        initializeGreeting();

        // Initialize menu cards and set click listeners
        initializeMenuCards();
    }

    private void initializeGreeting() {
        tvGreeting = findViewById(R.id.tvGreeting);

        // You could personalize the greeting here by getting user name from preferences or database
        // For example:
        // String userName = getUserNameFromPreferences();
        // if (userName != null && !userName.isEmpty()) {
        //     tvGreeting.setText("Hello, " + userName + "!");
        // }
    }

    private void initializeMenuCards() {
        // Find all card views
        budgetTrackerCard = findViewById(R.id.budgetTrackerCard);
        catatanHarianCard = findViewById(R.id.catatanHarianCard);
        todoListCard = findViewById(R.id.todoListCard);
        agendaCard = findViewById(R.id.agendaCard);

        todoListCard.setOnClickListener(view -> openTodoList());
        budgetTrackerCard.setOnClickListener(view -> openBudgetTracker());
        catatanHarianCard.setOnClickListener(view -> openCatatanHarian());
        agendaCard.setOnClickListener(view -> openAgenda());
    }

    // Methods to handle each menu item click

    private void openCatatanHarian() {
        Intent intent = new Intent(this, CatatanHarianActivity.class);
        startActivity(intent);
    }
    private void openBudgetTracker() {
        Intent intent = new Intent(this, BudgetTrackerActivity.class);
        startActivity(intent);
    }
    private void openTodoList() {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }
    private void openAgenda() {
        Intent intent = new Intent(this, AgendaActivity.class);
        startActivity(intent);
    }
}
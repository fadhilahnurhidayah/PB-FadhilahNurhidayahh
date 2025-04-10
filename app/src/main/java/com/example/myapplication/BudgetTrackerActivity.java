package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BudgetTrackerActivity extends AppCompatActivity {
    // UI Components
    private TextView tvIncome, tvExpenses, tvBalance;
    private EditText etAmount, etDescription;
    private RadioGroup rgType;
    private RadioButton rbIncome, rbExpense;
    private Button btnAdd;
    private ListView lvTransactions;

    // Data
    private ArrayList<String> transactionsList;
    private ArrayAdapter<String> adapter;
    private double totalIncome = 0;
    private double totalExpenses = 0;
    private double balance = 0;

    // Number formatter for currency
    private NumberFormat currencyFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker);

        // Initialize currency formatter
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

        // Initialize UI components
        initializeViews();
        setupTransactionsList();

        // Set click listener for Add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransaction();
            }
        });
    }

    private void initializeViews() {
        // TextViews for summary
        tvIncome = findViewById(R.id.tvIncome);
        tvExpenses = findViewById(R.id.tvExpenses);
        tvBalance = findViewById(R.id.tvBalance);

        // EditTexts for input
        etAmount = findViewById(R.id.etAmount);
        etDescription = findViewById(R.id.etDescription);

        // RadioGroup and RadioButtons
        rgType = findViewById(R.id.rgType);
        rbIncome = findViewById(R.id.rbIncome);
        rbExpense = findViewById(R.id.rbExpense);

        // Button
        btnAdd = findViewById(R.id.btnAdd);

        // ListView
        lvTransactions = findViewById(R.id.lvTransaction);

        // Set initial values
        updateSummary();
    }

    private void setupTransactionsList() {
        transactionsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transactionsList);
        lvTransactions.setAdapter(adapter);
    }

    private void addTransaction() {
        // Validate inputs
        if (validateInputs()) {
            // Get values
            String description = etDescription.getText().toString().trim();
            double amount = Double.parseDouble(etAmount.getText().toString().trim());
            boolean isIncome = rbIncome.isChecked();

            // Get current date and time
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String currentDateTime = sdf.format(new Date());

            // Update totals
            if (isIncome) {
                totalIncome += amount;
            } else {
                totalExpenses += amount;
            }
            balance = totalIncome - totalExpenses;
            updateSummary();

            // Create transaction string
            String transactionPrefix = isIncome ? "[+] " : "[-] ";
            String formattedAmount = currencyFormat.format(amount);
            String transaction = transactionPrefix + formattedAmount + " - " + description + " (" + currentDateTime + ")";

            // Add to list and update UI
            transactionsList.add(0, transaction); // Add to top of list
            adapter.notifyDataSetChanged();

            // Clear inputs
            etAmount.setText("");
            etDescription.setText("");
            etAmount.requestFocus();

            // Show success message
            Toast.makeText(this, "Transaction added", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs() {
        // Check amount
        if (etAmount.getText().toString().trim().isEmpty()) {
            etAmount.setError("Please enter an amount");
            return false;
        }

        // Check if valid number
        try {
            double amount = Double.parseDouble(etAmount.getText().toString().trim());
            if (amount <= 0) {
                etAmount.setError("Amount must be greater than zero");
                return false;
            }
        } catch (NumberFormatException e) {
            etAmount.setError("Invalid amount");
            return false;
        }

        // Check description
        if (etDescription.getText().toString().trim().isEmpty()) {
            etDescription.setError("Please enter a description");
            return false;
        }

        return true;
    }

    private void updateSummary() {
        tvIncome.setText(currencyFormat.format(totalIncome));
        tvExpenses.setText(currencyFormat.format(totalExpenses));
        tvBalance.setText(currencyFormat.format(balance));
    }
}
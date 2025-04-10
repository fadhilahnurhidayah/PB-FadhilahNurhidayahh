package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    private EditText etTask;
    private Button btnAddTask;
    private ListView lvTasks;

    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        // Inisialisasi komponen
        etTask = findViewById(R.id.etTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        lvTasks = findViewById(R.id.lvTasks);

        // Inisialisasi data list dan adapter
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lvTasks.setAdapter(adapter);

        // Tambah tugas
        btnAddTask.setOnClickListener(v -> {
            String task = etTask.getText().toString().trim();
            if (!task.isEmpty()) {
                taskList.add(0, task);
                adapter.notifyDataSetChanged();
                etTask.setText("");
            } else {
                Toast.makeText(this, "Masukkan tugas terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });

        // Hapus tugas saat diklik lama
        lvTasks.setOnItemLongClickListener((parent, view, position, id) -> {
            taskList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Tugas dihapus", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}

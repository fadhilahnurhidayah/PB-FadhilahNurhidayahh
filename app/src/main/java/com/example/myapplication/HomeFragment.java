package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private CardView budgetTrackerCard, catatanHarianCard, todoListCard, agendaCard;
    private TextView tvGreeting;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        initializeGreeting(view);
        initializeMenuCards(view);

        return view;
    }

    private void initializeGreeting(View view) {
        tvGreeting = view.findViewById(R.id.tvGreeting);

        // Contoh greeting statis, bisa diambil dari Firebase/SharedPreferences jika perlu
        // Misal:
        // String userName = getUserNameFromPreferences();
        // tvGreeting.setText("Hello, " + userName + "!");
        tvGreeting.setText("Halo!");
    }

    private void initializeMenuCards(View view) {
        budgetTrackerCard = view.findViewById(R.id.budgetTrackerCard);
        catatanHarianCard = view.findViewById(R.id.catatanHarianCard);
        todoListCard = view.findViewById(R.id.todoListCard);
        agendaCard = view.findViewById(R.id.agendaCard);

        budgetTrackerCard.setOnClickListener(v -> openActivity(BudgetTrackerActivity.class));
        catatanHarianCard.setOnClickListener(v -> openActivity(CatatanHarianActivity.class));
        todoListCard.setOnClickListener(v -> openActivity(TodoListActivity.class));
        agendaCard.setOnClickListener(v -> openActivity(AgendaActivity.class));
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(requireContext(), activityClass);
        startActivity(intent);
    }
}

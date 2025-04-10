package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    private CardView cardAccount, cardTheme, cardHelp, cardLogout;
    private FirebaseAuth mAuth;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        // Inisialisasi card views
        cardAccount = view.findViewById(R.id.cardAccount);
        cardTheme = view.findViewById(R.id.cardTheme);
        cardHelp = view.findViewById(R.id.cardHelp);
        cardLogout = view.findViewById(R.id.cardLogout);

        // Aksi klik untuk masing-masing card
        cardAccount.setOnClickListener(v -> {
            // Buka halaman pengaturan akun (contoh, gunakan Toast untuk demo)
            Toast.makeText(getActivity(), "Pengaturan Akun", Toast.LENGTH_SHORT).show();
            // Contoh: startActivity(new Intent(getActivity(), AccountSettingsActivity.class));
        });

        cardTheme.setOnClickListener(v -> {
            // Buka halaman pengaturan tema (contoh)
            Toast.makeText(getActivity(), "Pilih Tema", Toast.LENGTH_SHORT).show();
            // Contoh: startActivity(new Intent(getActivity(), ThemeSettingsActivity.class));
        });

        cardHelp.setOnClickListener(v -> {
            // Buka halaman bantuan/FAQ (contoh)
            Toast.makeText(getActivity(), "Bantuan & FAQ", Toast.LENGTH_SHORT).show();
            // Contoh: startActivity(new Intent(getActivity(), HelpActivity.class));
        });

        cardLogout.setOnClickListener(v -> {
            if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Gagal melakukan logout", Toast.LENGTH_SHORT).show();
                }
            });

        };
    }

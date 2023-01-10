package com.example.b3tempofertilati;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b3tempofertilati.databinding.ActivityHistory2Binding;

public class HistoryActivity2 extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivity2.class.getSimpleName();

    ActivityHistory2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistory2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
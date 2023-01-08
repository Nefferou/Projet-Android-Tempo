package com.example.b3tempofertilati;

import static com.example.b3tempofertilati.MainActivity.edfApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.b3tempofertilati.databinding.ActivityHistoryBinding;
import com.example.b3tempofertilati.databinding.ActivityMainBinding;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivity.class.getSimpleName();

    // Init views
    ActivityHistoryBinding binding;

    // Data model
    List<TempoDate> tempoDates = new ArrayList<>();

    // RV adapter
    TempoDateAdapter tempoDateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());

        // Init recycler view
        binding.tempoHistoryRv.setHasFixedSize(true);
        binding.tempoHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        tempoDateAdapter = new TempoDateAdapter(tempoDates, this);
        binding.tempoHistoryRv.setAdapter(tempoDateAdapter);
    }

    public void getTempoHistory() {
        Call<TempoHistory> call = edfApi.getTempoHistory("2021", "2022");
        call.enqueue(new Callback<TempoHistory>() {
            @Override
            public void onResponse(Call<TempoHistory> call, Response<TempoHistory> response) {
                tempoDates.clear();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    tempoDates.addAll(response.body().getTempoDates());
                    Log.d(LOG_TAG, "nb elements = " + tempoDates.size());
                    binding.tempoHistoryPb.setVisibility(View.GONE);
                }
                tempoDateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TempoHistory> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.tempoHistoryPb.setVisibility(View.VISIBLE);
        if(edfApi != null) {
            getTempoHistory();
        }
    }
}
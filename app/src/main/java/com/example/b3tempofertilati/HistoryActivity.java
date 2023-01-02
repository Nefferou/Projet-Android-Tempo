package com.example.b3tempofertilati;

import static com.example.b3tempofertilati.MainActivity.edfApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

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
        //setContentView(R.layout.activity_history);
        setContentView(binding.getRoot());

        // Init recycler view
        binding.tempoHistoryRv.setHasFixedSize(true);
        binding.tempoHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        tempoDateAdapter = new TempoDateAdapter(tempoDates, this);
        binding.tempoHistoryRv.setAdapter(tempoDateAdapter);

        if (edfApi != null) {
            // Create call to getTempoDaysLeft
            Call<TempoHistory> call = edfApi.getTempoHistory("2021", "2022");

            call.enqueue(new Callback<TempoHistory>() {
                @Override
                public void onResponse(@NonNull Call<TempoHistory> call, @NonNull Response<TempoHistory> response) {
                    tempoDates.clear();
                    if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                        tempoDates.addAll(response.body().getTempoDates());
                        Log.d(LOG_TAG,"nb elements = " + tempoDates.size());
                    }
                    tempoDateAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {

                }
            });

        }



    }
}
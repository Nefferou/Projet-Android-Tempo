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
        //setContentView(R.layout.activity_history);
        setContentView(binding.getRoot());

        // Init recycler view
        binding.tempoHistoryRv.setHasFixedSize(true);
        binding.tempoHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        tempoDateAdapter = new TempoDateAdapter(tempoDates, this);
        binding.tempoHistoryRv.setAdapter(tempoDateAdapter);

        if (edfApi != null) {
            updateTempoHistory();
        } else {
            Log.e(LOG_TAG, "Unable to init Retrofit client");
            finish();
        }

    }

    private void updateTempoHistory() {

        String yearNow = Tools.getNowDate("yyyy");
        String yearBefore = "";
        try {
            yearBefore = String.valueOf(Integer.parseInt(yearNow) - 1);
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, e.getMessage());
        }


        // Create call to getTempoHistory
        Call<TempoHistory> call = edfApi.getTempoHistory(yearBefore, yearNow);

        binding.tempoHistoryPb.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<TempoHistory>() {
            @Override
            public void onResponse(@NonNull Call<TempoHistory> call, @NonNull Response<TempoHistory> response) {
                tempoDates.clear();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    tempoDates.addAll(response.body().getTempoDates());
                    Log.d(LOG_TAG,"nb elements = " + tempoDates.size());
                } else {
                    Log.e(LOG_TAG,"Call to getTempoHistoy() returned error code " + response.code());
                }
                tempoDateAdapter.notifyDataSetChanged();
                binding.tempoHistoryPb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {
                Log.e(LOG_TAG,"Call to getTempoHistoy() failed");
                binding.tempoHistoryPb.setVisibility(View.GONE);
            }
        });
    }

}
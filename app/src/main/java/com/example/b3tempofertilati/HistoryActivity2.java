package com.example.b3tempofertilati;

import static com.example.b3tempofertilati.MainActivity.edfApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.b3tempofertilati.databinding.ActivityHistory2Binding;
import com.example.b3tempofertilati.databinding.ActivityMainBinding;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity2 extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivity.class.getSimpleName();

    // Init views
    ActivityHistory2Binding binding;

    // Data model
    List<TempoDate> tempoDates = new ArrayList<>();

    // RV adapter
    TempoDateAdapter tempoDateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistory2Binding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_history);
        setContentView(binding.getRoot());

        // Init recycler view
        binding.tempoHistoryRv2.setHasFixedSize(true);
        binding.tempoHistoryRv2.setLayoutManager(new LinearLayoutManager(this));
        tempoDateAdapter = new TempoDateAdapter(tempoDates, this);
        binding.tempoHistoryRv2.setAdapter(tempoDateAdapter);

        if (edfApi != null) {
            updateTempoHistory();
        } else {
            Log.e(LOG_TAG, "Unable to init Retrofit client");
            finish();
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.years_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
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
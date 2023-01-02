package com.example.b3tempofertilati;

import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String LOG_TAG=ApiClient.class.getSimpleName();
    private static final String EDFAPI_BASE_URL = "https://particulier.edf.fr";

    private static Retrofit retrofit = null;

    static Retrofit get() {

        // set BASIC HTTP trace level
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        // Create a HTTP Header interceptor
        Interceptor headerInterceptor = new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        };

        // Create an HTTP Client
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)  // logging always at the end
                .build();

        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(EDFAPI_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return retrofit;

    }

}

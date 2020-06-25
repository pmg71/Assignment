package com.example.myapplication;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String base_url = "https://reqres.in/api/";
    public static Retrofit retrofit;
    public static OkHttpClient okHttpClient = new OkHttpClient();

    public static Retrofit getApliClient() {
        retrofit = new Retrofit.Builder().baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

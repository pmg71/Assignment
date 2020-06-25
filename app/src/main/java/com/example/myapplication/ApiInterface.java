package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users?page=1&delay=3")
    Call<DbResponse> getListOfData();
}

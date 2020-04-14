package com.example.lab7;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("positions.json")
    Call<List<Job>> getJobs();
}

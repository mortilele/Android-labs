package com.example.lab7;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("positions.json")
    Call<List<Job>> getJobs();

    @GET("positions/{id}.json")
    Call<Job> getJobById(@Path("id") String jobId);
}

package com.example.ocenika.service;

import com.example.ocenika.model.ProfessorList;
import com.example.ocenika.model.UniversityList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("api/universities/")
    Call<List<UniversityList>> getUniversities();

    @GET("api/professors/")
    Call<List<ProfessorList>> getProfessors();
}

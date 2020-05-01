package com.example.ocenika.service;

import com.example.ocenika.model.Comment;
import com.example.ocenika.model.Professor;
import com.example.ocenika.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("api/universities/")
    Call<List<University>> getUniversities();

    @GET("api/professors/")
    Call<List<Professor>> getProfessors();

    @GET("api/professors/")
    Call<List<Professor>> getProfessorsByUniversity(@Query("universities") int universityId);

    @GET("api/professors/{id}")
    Call<Professor> getProfessorById(@Path("id") int professorId);

    @POST("api/ratings/")
    Call<Comment> addComment(@Body Comment comment,
                                  @Header("authorization") String token);
}

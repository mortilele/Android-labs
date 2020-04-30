package com.example.ocenika.service;

import com.example.ocenika.model.Comment;
import com.example.ocenika.model.ProfessorList;
import com.example.ocenika.model.UniversityList;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @GET("api/universities/")
    Call<List<UniversityList>> getUniversities();

    @GET("api/professors/")
    Call<List<ProfessorList>> getProfessors();

    @GET("api/professors/{id}")
    Call<ProfessorList> getProfessorById(@Path("id") int professorId);

    @POST("api/ratings/")
    Call<ResponseBody> addComment(@Body Comment comment,
                                  @Header("authorization") String token);
}

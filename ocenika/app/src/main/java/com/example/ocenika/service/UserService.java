package com.example.ocenika.service;

import com.example.ocenika.model.Login;
import com.example.ocenika.model.Token;

import retrofit2.Call;
import retrofit2.http.POST;

public interface UserService {

    @POST("login")
    Call<Token> login(Login login);


}

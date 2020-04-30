package com.example.ocenika.service;

import com.example.ocenika.model.Login;
import com.example.ocenika.model.Token;
import com.example.ocenika.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth/login/")
    Call<Token> login(@Body Login login);


    @POST("auth/users/")
    Call<ResponseBody> createUser(@Body User user);

}

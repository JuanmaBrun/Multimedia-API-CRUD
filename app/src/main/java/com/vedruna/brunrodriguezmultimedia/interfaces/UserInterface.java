package com.vedruna.brunrodriguezmultimedia.interfaces;

import com.vedruna.brunrodriguezmultimedia.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserInterface {

    @GET("/api/v1/users/getAllUsers")
    Call<List<User>> getAllUsers();

    @POST("auth/register")
    Call<User> register(@Body User user);
}

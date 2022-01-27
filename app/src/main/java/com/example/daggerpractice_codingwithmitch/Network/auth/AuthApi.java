package com.example.daggerpractice_codingwithmitch.Network.auth;

import com.example.daggerpractice_codingwithmitch.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int id);
}

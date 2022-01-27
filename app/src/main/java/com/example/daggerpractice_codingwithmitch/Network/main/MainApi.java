package com.example.daggerpractice_codingwithmitch.Network.main;

import com.example.daggerpractice_codingwithmitch.Models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    //    posts?userId=1
    @GET("posts")
    Call<List<Post>> getPostsFromUser(@Query("userId") int id);
}

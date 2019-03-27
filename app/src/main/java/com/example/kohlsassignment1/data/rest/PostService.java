package com.example.kohlsassignment1.data.rest;

import com.example.kohlsassignment1.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    @GET("{directory}")
    Call<List<Post>> getPosts(@Path("directory") String directory);
}

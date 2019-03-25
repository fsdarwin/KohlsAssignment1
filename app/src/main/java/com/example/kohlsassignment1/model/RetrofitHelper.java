package com.example.kohlsassignment1.model;

import com.example.kohlsassignment1.model.Pojos.JsonReturn;

import java.net.URL;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitHelper {

    private static Retrofit createRetrofitCall (String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public interface apiCall {
        @GET("posts")
        Flowable<List<JsonReturn>> getPosts();
    }
}

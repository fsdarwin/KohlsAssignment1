package com.example.kohlsassignment1.di.module;

import com.example.kohlsassignment1.data.rest.PostService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.kohlsassignment1.Constants.BASE_URL;

@Singleton
@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static PostService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(PostService.class);
    }
}
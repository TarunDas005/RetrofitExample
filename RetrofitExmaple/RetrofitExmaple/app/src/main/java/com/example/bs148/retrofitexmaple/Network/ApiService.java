package com.example.bs148.retrofitexmaple.Network;

import com.example.bs148.retrofitexmaple.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by BS148 on 8/18/2016.
 */
public interface ApiService {
    @GET("movie/top_rated")
    Call<Example> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Example> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}

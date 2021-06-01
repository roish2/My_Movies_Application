package com.example.mymoviesapplication.networking

import com.example.mymoviesapplication.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesCallsInterface {

    @Headers("Authorization:Bearer ${RetrofitClient.Authorization_Key}")
//    @Headers("Authorization:${RetrofitClient.Authorization_Key}", "Content-Type: application/json;charset=utf-8")
    @GET("3/discover/movie")
    fun getMovies():Call<MovieResponse>
}
package com.example.mymoviesapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapplication.model.MovieMainObject
import com.example.mymoviesapplication.model.MovieResponse
import com.example.mymoviesapplication.networking.MoviesCallsInterface
import com.example.mymoviesapplication.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel: ViewModel() {

    val retrofitService: MoviesCallsInterface?
    var steps = MutableLiveData<Steps>()
    companion object{
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }


    init {
        val retrofitClient: Retrofit? = RetrofitClient.createClient()
        retrofitService = retrofitClient?.create(MoviesCallsInterface::class.java)
    }

    fun getMovies(){
        val moviesCall = retrofitService?.getMovies()
        moviesCall?.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    steps.postValue(Steps.DataReady(it))
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                steps.postValue(Steps.OnError("error"))
            }

        })

    }

    fun goToMovieDetails(data: MovieMainObject){
        steps.postValue(Steps.movieDetails(data))
    }



    sealed class Steps{
        class DataReady(val data: MovieResponse) : Steps()
        class movieDetails(val data: MovieMainObject) : Steps()
        class OnError(val errorMessage:String) : Steps()
    }
}
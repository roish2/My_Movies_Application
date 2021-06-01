package com.example.mymoviesapplication.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private var retrofit: Retrofit?= null
        const val baseUrl:String = "https://api.themoviedb.org/"
        const val Authorization_Key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MjYzYjZjYzBjNWVjYTZiMTI5MWIyZWU0OWFlNTIxNCIsInN1YiI6IjVkNjJiMWJiN2Y2YzhkMDNhY2VjMTc0YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-Zdj1UvBn5JRjYaI-v-bfbE817qOBIt67AcF9QXD_8M"



        fun createClient(): Retrofit?{
            

            if (retrofit == null){
                retrofit = Retrofit.Builder()

                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }
    }
}
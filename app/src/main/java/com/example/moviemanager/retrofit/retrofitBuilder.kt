package com.example.moviemanager.retrofit

import com.example.moviemanager.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofitBuilder {
     fun prepareRetrofit():IMoviesRetrofit{
         val retrofit= Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         return retrofit.create(IMoviesRetrofit::class.java)
     }
}
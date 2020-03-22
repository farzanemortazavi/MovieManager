package com.example.moviemanager.retrofit

import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.pojo.searchResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMoviesRetrofit {
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId:Long,@Query("api_key")  apiKey:String)
            :Observable<movieResponseModel>

    @GET("search/movie")
    fun getMovieList(@Query("api_key")  apiKey:String,@Query("query")  searchPhrase:String)
            :Observable<searchResponseModel>


}
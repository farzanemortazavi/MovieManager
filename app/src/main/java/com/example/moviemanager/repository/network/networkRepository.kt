package com.example.moviemanager.repository.network

import android.util.Log
import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.pojo.searchResponseModel
import com.example.moviemanager.retrofit.retrofitBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class networkRepository() {

    private val iretrofit= retrofitBuilder().prepareRetrofit()

    fun getMovieList(searchPhrase:String,apiKey:String ):Observable<searchResponseModel>{
        return iretrofit.getMovieList(apiKey,searchPhrase)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovieDetails(id:Int,apiKey: String):Observable<movieResponseModel>{
        return iretrofit.getMovieDetails(id,apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
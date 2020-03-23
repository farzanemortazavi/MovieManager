package com.example.moviemanager.repository.network

import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.pojo.searchResponseModel
import com.example.moviemanager.retrofit.IMoviesRetrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class networkRepository(private val iretrofit: IMoviesRetrofit) {

    fun getMovieList(saerchPhrase:String,apiKey:String ):Observable<searchResponseModel>{
        return iretrofit.getMovieList(apiKey,saerchPhrase)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovieDetails(id:Long,apiKey: String):Observable<movieResponseModel>{
        return iretrofit.getMovieDetails(id,apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
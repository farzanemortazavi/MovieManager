package com.example.moviemanager.repository

import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.pojo.searchResponseModel
import com.example.moviemanager.repository.network.networkRepository
import io.reactivex.Observable

class repositoryClass() {

    private val networkRepository= networkRepository()
    fun getMovieList(saerchPhrase:String,apiKey:String ): Observable<searchResponseModel> {
        return networkRepository.getMovieList(apiKey,saerchPhrase)

    }

    fun getMovieDetails(id:Int,apiKey: String):Observable<movieResponseModel>{
        return networkRepository.getMovieDetails(id,apiKey)

    }
}
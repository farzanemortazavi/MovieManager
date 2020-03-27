package com.example.moviemanager.repository

import android.content.Context
import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.pojo.searchResponseModel
import com.example.moviemanager.repository.local.localRepository
import com.example.moviemanager.repository.network.networkRepository
import com.example.moviemanager.room.movieTable
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class repositoryClass @Inject constructor(private val network:networkRepository) {

    //private val network= networkRepository()
    //private val local=localRepository()

    fun getMovieList(saerchPhrase:String,apiKey:String ): Observable<searchResponseModel> {
        return network.getMovieList(apiKey,saerchPhrase)

    }
    //-------------------------------------------------------
    fun getMovieDetails(id:Int,apiKey: String):Observable<movieResponseModel>{
        return network.getMovieDetails(id,apiKey)

    }
    //--------------------------------------------------------
    /*fun getOfflineMoveList(): Single<List<movieTable>> {
        return local.getOfflineList()
    }
    //--------------------------------------------------------
    fun saveMovieDetails(movie:movieTable): Completable {
        return local.saveMovieDetails(movie)
    }
    //--------------------------------------------------------
    fun isExistMovie(id:Int):Single<Boolean>{
        return local.isExistMovie(id)
    }
    //--------------------------------------------------------
    fun deleteMovie(id:Int):Completable{
        return local.deleteMovie(id)
    }
    //--------------------------------------------------------
    fun getMovieFromDB(id: Int): Single<movieTable>{
        return local.getMovieFromDB(id)
    }*/


}
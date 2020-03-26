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

class repositoryClass() {

    private val network= networkRepository()



    fun getMovieList(saerchPhrase:String,apiKey:String ): Observable<searchResponseModel> {
        return network.getMovieList(apiKey,saerchPhrase)

    }
    //-------------------------------------------------------
    fun getMovieDetails(id:Int,apiKey: String):Observable<movieResponseModel>{
        return network.getMovieDetails(id,apiKey)

    }
    //--------------------------------------------------------
    fun getOfflineMoveList(context:Context): Single<List<movieTable>> {
        val local=localRepository(context)
        return local.getOfflineList()
    }
    //--------------------------------------------------------
    fun saveMovieDetails(movie:movieTable,context:Context): Completable {
        val local=localRepository(context)
        return local.saveMovieDetails(movie)
    }
    //--------------------------------------------------------
    fun isExistMovie(id:Int,context:Context):Single<Boolean>{
        val local=localRepository(context)
        return local.isExistMovie(id)
    }
    //--------------------------------------------------------
    fun deleteMovie(id:Int,context:Context):Completable{
        val local=localRepository(context)
        return local.deleteMovie(id)
    }
    //--------------------------------------------------------
    fun getMovieFromDB(id: Int,context:Context): Single<movieTable>{
        val local=localRepository(context)
        return local.getMovieFromDB(id)
    }


}
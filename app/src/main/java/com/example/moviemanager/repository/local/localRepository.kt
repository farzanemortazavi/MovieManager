package com.example.moviemanager.repository.local

import android.content.Context
import com.example.moviemanager.room.ImovieDao
import com.example.moviemanager.room.databaseBuilder
import com.example.moviemanager.room.movieTable
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class localRepository @Inject constructor(val dbDao:ImovieDao) {
    //private val db=databaseBuilder.getDatabasenIstance(context)


    fun saveMovieDetails(movie:movieTable):Completable{
        return dbDao.insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    //----------------------------------------------------
    fun getOfflineList(): Single<List<movieTable>> {
        return dbDao.getAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    //-------------------------------------------------
    fun isExistMovie(id:Int):Single<Boolean>{
        return dbDao.isExistMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    //-------------------------------------------------
    fun deleteMovie(id:Int):Completable{
        return dbDao.deleteMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    //-------------------------------------------------
    fun getMovieFromDB(id: Int): Single<movieTable>{
        return dbDao.getMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
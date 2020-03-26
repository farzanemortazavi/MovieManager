package com.example.moviemanager.repository.local

import android.content.Context
import com.example.moviemanager.room.databaseBuilder
import com.example.moviemanager.room.movieTable
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class localRepository(context:Context) {
    private val db=databaseBuilder.getDatabasenIstance(context)

    fun saveMovieDetails(movie:movieTable):Completable{
        return db.ImovieDao().insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    //----------------------------------------------------
    fun getOfflineList(): Single<List<movieTable>> {
        return db.ImovieDao().getAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    //-------------------------------------------------
    fun isExistMovie(id:Int):Single<Boolean>{
        return db.ImovieDao().isExistMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    //-------------------------------------------------
    fun deleteMovie(id:Int):Completable{
        return db.ImovieDao().deleteMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    //-------------------------------------------------
    fun getMovieFromDB(id: Int): Single<movieTable>{
        return db.ImovieDao().getMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
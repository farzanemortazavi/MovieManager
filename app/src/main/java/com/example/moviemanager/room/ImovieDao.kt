package com.example.moviemanager.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ImovieDao {
    @Query("SELECT * FROM movieTable")
    fun getAllMovies(): Single<List<movieTable>>

    @Query("SELECT * FROM movieTable WHERE id = (:id)")
    fun getMovie(id: Int): Single<movieTable>

    @Query("SELECT COUNT(*)>0 from movieTable WHERE id =(:id)")
    fun isExistMovie(id: Int): Single<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieTable: movieTable):Completable

    @Query("DELETE from movieTable where id = (:id)")
    fun deleteMovie(id: Int):Completable


}
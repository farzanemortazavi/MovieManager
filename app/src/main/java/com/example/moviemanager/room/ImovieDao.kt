package com.example.moviemanager.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImovieDao {
    @Query("SELECT * FROM movieTable")
    fun getAllMovies(): List<movieTable>

    @Query("SELECT * FROM movieTable WHERE id = (:id)")
    fun getMovie(id: Int): movieTable

    @Query("SELECT COUNT(*)>0 from movieTable WHERE id =(:id)")
    fun isExistMovie(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieTable: movieTable)

    @Query("DELETE from movieTable where id = (:id)")
    fun deleteMovie(id: Int)


}
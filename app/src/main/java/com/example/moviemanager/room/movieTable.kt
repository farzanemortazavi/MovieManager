package com.example.moviemanager.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class movieTable (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "rank") val rank: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String,
    @ColumnInfo(name = "backdrop_url") val backdropUrl: String


)
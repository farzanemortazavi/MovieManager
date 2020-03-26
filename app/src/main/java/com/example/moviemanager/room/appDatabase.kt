package com.example.moviemanager.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [movieTable::class], version = 1, exportSchema = false)

abstract class appDatabase: RoomDatabase() {
    abstract fun ImovieDao():ImovieDao
}
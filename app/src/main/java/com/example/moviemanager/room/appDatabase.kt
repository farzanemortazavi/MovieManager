package com.example.moviemanager.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [movieTable::class], version = 1)

abstract class appDatabase: RoomDatabase() {
    abstract fun ImovieDao():ImovieDao
}
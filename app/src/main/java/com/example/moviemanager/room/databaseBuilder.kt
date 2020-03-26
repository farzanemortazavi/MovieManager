package com.example.moviemanager.room

import android.content.Context
import androidx.room.Room
import com.example.moviemanager.utils.DATABASE_NAME

class databaseBuilder {

    companion object {
        @Volatile
        private var databseInstance: appDatabase? = null

        fun getDatabasenIstance(mContext: Context): appDatabase =
            databseInstance ?: synchronized(this) {
                databseInstance ?: buildDatabaseInstance(mContext).also {
                    databseInstance = it
                }
            }

        fun buildDatabaseInstance(context: Context): appDatabase {
            return Room.databaseBuilder(
                context, appDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }





}
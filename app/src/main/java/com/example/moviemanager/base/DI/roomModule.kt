package com.example.moviemanager.base.DI

import android.app.Application
import androidx.room.Room
import com.example.moviemanager.retrofit.IMoviesRetrofit
import com.example.moviemanager.room.ImovieDao
import com.example.moviemanager.room.appDatabase
import com.example.moviemanager.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class roomModule {
    @Singleton
    @Provides
    fun database(application: Application):appDatabase {
        return Room.databaseBuilder(
            application.applicationContext, appDatabase::class.java, DATABASE_NAME
        ).build()

    }
    //-------------------------------------------------------
    @Singleton
    @Provides
    fun provideMovieDao(db:appDatabase):ImovieDao{
        return db.ImovieDao()
    }
    //-------------------------------------------------------


}
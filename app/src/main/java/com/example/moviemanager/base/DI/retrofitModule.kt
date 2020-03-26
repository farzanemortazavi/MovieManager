package com.example.moviemanager.base.DI

import com.example.moviemanager.retrofit.IMoviesRetrofit
import com.example.moviemanager.utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class retrofitModule {

    @Singleton
    @Provides
    fun retrofitProvider(): IMoviesRetrofit {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(IMoviesRetrofit::class.java)
    }
}
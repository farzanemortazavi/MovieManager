package com.example.moviemanager.base.DI

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [retrofitModule::class,viewModelFactoryProvider::class])
interface factoryComplonent {
    fun provideVMFactory():viewModelFactory
}
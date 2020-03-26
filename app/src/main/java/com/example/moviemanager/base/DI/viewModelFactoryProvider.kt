package com.example.moviemanager.base.DI

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class viewModelFactoryProvider {

    abstract fun viewModelFactoryProvider(factory: viewModelFactory): ViewModelProvider.Factory
}
package com.example.moviemanager.base.DI

import android.app.Application
import com.example.moviemanager.base.baseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [retrofitModule::class,viewModelFactoryProvider::class,roomModule::class])
interface appComponent : AndroidInjector<baseApplication> {

    /**
     * Builder to bind application to modules
     */
    @Component.Builder
    interface Builder {

        /**
         * Gets application instance
         */
        @BindsInstance
        fun application(application: Application): Builder

        /**
         * Builds the component
         */
        fun build(): appComponent
    }
}
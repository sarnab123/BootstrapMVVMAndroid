package com.kohls.bootstrap.bootstrapapp.di

import dagger.Module
import dagger.Provides
import com.kohls.bootstrap.bootstrapapp.AppLifecycleCallbacks
import com.kohls.bootstrap.bootstrapapp.data.di.DataModule
import javax.inject.Singleton

@Module(includes = [DataModule::class])
internal object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()

}
package com.kohls.bootstrap.bootstrapapp.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
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

    @Provides
    @Singleton
    @JvmStatic
    fun provideSharedPreferences(application: Application) : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }


}
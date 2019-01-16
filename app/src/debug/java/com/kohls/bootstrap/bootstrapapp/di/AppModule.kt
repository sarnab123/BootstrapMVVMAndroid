package com.kohls.bootstrap.bootstrapapp.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import com.kohls.bootstrap.bootstrapapp.AppLifecycleCallbacks
import com.kohls.bootstrap.bootstrapapp.data.db.ProductDao
import com.kohls.bootstrap.bootstrapapp.data.db.ProductDatabase
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

    @Provides
    @Singleton
    @JvmStatic
    fun providesProductDatabase(context: Context): ProductDatabase{
        return Room.databaseBuilder(context, ProductDatabase::class.java, "productDB").build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideUserDao(productDatabase: ProductDatabase): ProductDao {
        return productDatabase.productDao()
    }


}
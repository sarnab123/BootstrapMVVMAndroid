package com.kohls.bootstrap.bootstrapapp.data.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import com.kohls.bootstrap.bootstrapapp.data.api.LiveDataCallAdapterFactory
import com.kohls.bootstrap.bootstrapapp.data.api.ProductService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
internal object DataModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideMoshi() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY })
        .addInterceptor(HeaderInterceptor())
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(oktHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl("https://mapps.kohls.com")
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideProductListService(retrofit: Retrofit): ProductService = retrofit.create(ProductService::class.java)

}
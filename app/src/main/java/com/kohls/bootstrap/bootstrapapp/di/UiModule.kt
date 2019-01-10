package com.kohls.bootstrap.bootstrapapp.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.kohls.bootstrap.bootstrapapp.ui.main.MainActivity
import com.kohls.bootstrap.bootstrapapp.di.module.MainModule

@Module
internal abstract class UiModule {

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @ContributesAndroidInjector(modules = [MainModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

}
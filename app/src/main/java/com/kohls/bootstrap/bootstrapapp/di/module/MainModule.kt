package com.kohls.bootstrap.bootstrapapp.di.module

import android.arch. lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import com.kohls.bootstrap.bootstrapapp.di.ViewModelKey
import com.kohls.bootstrap.bootstrapapp.ui.main.MainFragment
import com.kohls.bootstrap.bootstrapapp.ui.main.MainViewModel

@Module
internal abstract class MainModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

  @ContributesAndroidInjector
  abstract fun contributeMainFragment(): MainFragment

}
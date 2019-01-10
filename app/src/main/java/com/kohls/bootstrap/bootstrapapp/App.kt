package com.kohls.bootstrap.bootstrapapp

import com.kohls.bootstrap.bootstrapapp.AppLifecycleCallbacks
import com.kohls.bootstrap.bootstrapapp.di.DaggerAppComponent
import com.kohls.bootstrap.bootstrapapp.di.applyAutoInjector
import dagger.android.support.DaggerApplication
import com.kohls.bootstrap.bootstrapapp.ui.app.UserViewModel
import javax.inject.Inject

/**
 * Android Application class
 *
 * Purpose :
 * 1) Maintain Application LifeCycleCallbacks
 * 2) Dagger Application - Injector for ViewModels
 *
 */


class App : DaggerApplication() {

  @Inject lateinit var appLifecycleCallbacks: AppLifecycleCallbacks
  @Inject lateinit var userViewModel: UserViewModel

  override fun applicationInjector() = DaggerAppComponent.builder()
      .application(this)
      .build()

  override fun onCreate() {
    super.onCreate()
    applyAutoInjector()
    appLifecycleCallbacks.onCreate(this)
    userViewModel.loginUserId.value = "sarnab123"
  }

  override fun onTerminate() {
    appLifecycleCallbacks.onTerminate(this)
    super.onTerminate()
  }

}
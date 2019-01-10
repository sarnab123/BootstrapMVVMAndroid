package com.kohls.bootstrap.bootstrapapp.di

import android.app.Application
import com.kohls.bootstrap.bootstrapapp.AppLifecycleCallbacks
import timber.log.Timber

class DebugAppLifecycleCallbacks : AppLifecycleCallbacks {

  override fun onCreate(application: Application) {
    Timber.plant(Timber.DebugTree())
  }

  override fun onTerminate(application: Application) {

  }
}

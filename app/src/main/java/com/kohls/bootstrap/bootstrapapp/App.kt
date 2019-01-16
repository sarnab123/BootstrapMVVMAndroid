package com.kohls.bootstrap.bootstrapapp

import com.kohls.bootstrap.bootstrapapp.di.DaggerAppComponent
import com.kohls.bootstrap.bootstrapapp.di.applyAutoInjector
import dagger.android.support.DaggerApplication
import com.kohls.bootstrap.bootstrapapp.ui.app.ProductDimViewModel
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
  @Inject lateinit var productDimViewModel: ProductDimViewModel

  override fun applicationInjector() = DaggerAppComponent.builder()
      .application(this)
      .build()

  override fun onCreate() {
    super.onCreate()
    applyAutoInjector()
    appLifecycleCallbacks.onCreate(this)
    productDimViewModel.productDimensionId.value = "Gender:Mens Silhouette:Button-Down Shirts Category:Tops Department:Clothing"
  }

  override fun onTerminate() {
    appLifecycleCallbacks.onTerminate(this)
    super.onTerminate()
  }

}
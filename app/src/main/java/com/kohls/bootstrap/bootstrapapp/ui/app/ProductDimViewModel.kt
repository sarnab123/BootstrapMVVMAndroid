package com.kohls.bootstrap.bootstrapapp.ui.app

import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDimViewModel @Inject constructor() {

  val productDimensionId = MutableLiveData<String>()
}
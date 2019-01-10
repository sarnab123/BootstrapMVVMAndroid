package com.kohls.bootstrap.bootstrapapp.ui.app

import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserViewModel @Inject constructor() {

  val loginUserId = MutableLiveData<String>()
}
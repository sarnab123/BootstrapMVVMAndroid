package com.kohls.bootstrap.bootstrapapp.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.view.View
import com.kohls.bootstrap.bootstrapapp.SingleLiveEvent
import com.kohls.bootstrap.bootstrapapp.data.model.Payload
import com.kohls.bootstrap.bootstrapapp.data.model.ProductList
import com.kohls.bootstrap.bootstrapapp.data.model.Repo
import com.kohls.bootstrap.bootstrapapp.data.repository.GitHubRepository
import com.kohls.bootstrap.bootstrapapp.data.repository.ProductListRepository
import com.kohls.bootstrap.bootstrapapp.util.AbsentLiveData
import com.kohls.bootstrap.bootstrapapp.util.ext.toLiveData
import com.kohls.bootstrap.bootstrapapp.util.ext.switchMap
import com.kohls.bootstrap.bootstrapapp.util.ext.switchMapForApiResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: ProductListRepository) : ViewModel() {

    val productID = MutableLiveData<String>()
    private var urlOptions : HashMap<String,String> = HashMap()
    val productList: LiveData<ProductList?>
    val loadData : SingleLiveEvent<Unit> = SingleLiveEvent()

  init {

      urlOptions["channel"] = "android"
      urlOptions["inStoreEnabled"] = "false"
      urlOptions["isDefaultStore"] = "true"
      urlOptions["limit"] = "12"
      urlOptions["offset"] = "1"


      productList = productID.switchMap {
            if(it.isEmpty()) AbsentLiveData.create()
            else Transformations.switchMap(loadData) {
                switchMapForApiResponse(repository.loadProducts(productID.value!!, urlOptions), doOnSuccess = {
                    return@switchMapForApiResponse it
                }, doOnSubscribe = {
                }, doOnError = {
                    Timber.d(it)
                })
            }
      }
      loadData.call()
  }

}
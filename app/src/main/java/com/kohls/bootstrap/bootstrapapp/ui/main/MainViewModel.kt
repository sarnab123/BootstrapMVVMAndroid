package com.kohls.bootstrap.bootstrapapp.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kohls.bootstrap.bootstrapapp.data.model.Repo
import com.kohls.bootstrap.bootstrapapp.data.repository.GitHubRepository
import com.kohls.bootstrap.bootstrapapp.util.AbsentLiveData
import com.kohls.bootstrap.bootstrapapp.util.ext.toLiveData
import com.kohls.bootstrap.bootstrapapp.util.ext.switchMap
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: GitHubRepository) : ViewModel() {

  val ownerId = MutableLiveData<String>()
  val repos: LiveData<List<Repo>>

  init {
    repos = ownerId.switchMap {
        if (it.isEmpty()) AbsentLiveData.create()
        else repository.loadRepos(it)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Flowable.empty())
            .toLiveData()
    }
  }

}
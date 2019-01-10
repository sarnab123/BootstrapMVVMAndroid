package com.kohls.bootstrap.bootstrapapp.data.api

/**
 * Retrofit service to make API calls.
 */
import io.reactivex.Flowable
import com.kohls.bootstrap.bootstrapapp.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

  @GET("/users/{user}/repos")
  fun listRepos(@Path("user") user: String): Flowable<List<Repo>>

}
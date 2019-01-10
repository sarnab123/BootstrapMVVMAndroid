package com.kohls.bootstrap.bootstrapapp.data.repository

import com.kohls.bootstrap.bootstrapapp.data.api.GitHubService
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val gitHubService: GitHubService) {

  fun loadRepos(owner: String) = gitHubService.listRepos(owner)

}

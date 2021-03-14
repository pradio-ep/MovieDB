package com.pradioep.test.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.test.BuildConfig
import com.pradioep.test.model.Error
import com.pradioep.test.model.MovieItem
import com.pradioep.test.model.Response
import com.pradioep.test.repository.Repository
import com.pradioep.test.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): BaseViewModel() {

    val listMovie = MutableLiveData<ArrayList<MovieItem>>()

    fun getCategory(category : Int) {
        isLoading.value = true
        viewModelScope.launch {
            val response = when (category) {
                1 -> {
                    repository.popular(BuildConfig.API_KEY, "en-US", 1)
                }
                2 -> {
                    repository.upcoming(BuildConfig.API_KEY, "en-US", 1)
                }
                3 -> {
                    repository.topRated(BuildConfig.API_KEY, "en-US", 1)
                }
                else -> {
                    repository.nowPlaying(BuildConfig.API_KEY, "en-US", 1)
                }
            }
            when (response) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listMovie.value = response.body.results
                }
                is NetworkResponse.ServerError -> {
                    isLoading.value = false
                    serverError.value = response.body?.status_message
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    networkError.value = response.error.message
                }
            }
        }
    }
}
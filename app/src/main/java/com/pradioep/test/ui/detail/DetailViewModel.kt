package com.pradioep.test.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.test.BuildConfig
import com.pradioep.test.model.MovieDetail
import com.pradioep.test.model.MovieReview
import com.pradioep.test.repository.Repository
import com.pradioep.test.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): BaseViewModel() {

    val movieDetail = MutableLiveData<MovieDetail>()
    val listMovieReview = MutableLiveData<ArrayList<MovieReview>>()

    fun getMovieDetail(movieId : Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.movieDetail(movieId, BuildConfig.API_KEY, "en-US")) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    movieDetail.value = response.body
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

    fun getMovieReview(movieId : Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.movieReview(movieId, BuildConfig.API_KEY, "en-US", 1)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listMovieReview.value = response.body.results
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
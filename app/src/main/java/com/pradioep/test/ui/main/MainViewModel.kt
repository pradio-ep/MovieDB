package com.pradioep.test.ui.main

import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.test.BuildConfig
import com.pradioep.test.repository.Repository
import com.pradioep.test.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): BaseViewModel() {

    fun getPopular() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.popular(BuildConfig.API_KEY, "en-US", 1)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                }
                is NetworkResponse.ServerError -> {
                    isLoading.value = false
                    serverError.value = response.body?.error
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    networkError.value = response.error.message
                }
            }
        }
    }
}
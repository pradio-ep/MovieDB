package com.pradioep.test.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val showMessage : MutableLiveData<String> = MutableLiveData()
    val serverError : MutableLiveData<String> = MutableLiveData()
    val networkError : MutableLiveData<String> = MutableLiveData()
}
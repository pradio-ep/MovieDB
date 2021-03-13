package com.pradioep.test.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.test.BuildConfig
import com.pradioep.test.model.Error
import com.pradioep.test.model.Movie
import com.pradioep.test.model.Response
import com.pradioep.test.repository.Repository
import com.pradioep.test.ui.base.BaseViewModel
import com.pradioep.test.util.SingleLiveEvent
import kotlinx.coroutines.launch

class DetailViewModel(): BaseViewModel() {

    val clickFavorite = SingleLiveEvent<Unit>()

    fun onClickFavorite() {
        clickFavorite.call()
    }
}
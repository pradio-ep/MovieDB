package com.pradioep.test.di

import com.pradioep.test.ui.detail.DetailViewModel
import com.pradioep.test.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel() }
}
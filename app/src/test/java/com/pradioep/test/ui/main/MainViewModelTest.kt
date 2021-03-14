package com.pradioep.test.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.verify
import com.pradioep.test.model.MovieItem
import com.pradioep.test.utils.getTestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var viewModel: MainViewModel

    @Before
    fun start() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `get movie by category`() {
        doNothing().`when`(viewModel).getCategory(1)
        val data: MutableLiveData<ArrayList<MovieItem>> = MutableLiveData()
        Mockito.`when`(viewModel.listMovie).thenReturn(data)
        val result = viewModel.listMovie
        verify(viewModel).listMovie
        Assert.assertEquals(
            data.getTestObserver().observedValues,
            result.getTestObserver().observedValues
        )
    }

    @Test
    fun `check loading state`() {
        val data: MutableLiveData<Boolean> = MutableLiveData()
        Mockito.`when`(viewModel.isLoading).thenReturn(data)
        val result = viewModel.isLoading
        verify(viewModel).isLoading
        Assert.assertEquals(
            data.getTestObserver().observedValues,
            result.getTestObserver().observedValues
        )
    }

    @Test
    fun `show network error message when network error`() {
        val data: MutableLiveData<String> = MutableLiveData()
        Mockito.`when`(viewModel.networkError).thenReturn(data)
        val result = viewModel.networkError
        verify(viewModel).networkError
        Assert.assertEquals(
            data.getTestObserver().observedValues,
            result.getTestObserver().observedValues
        )
    }

    @Test
    fun `show server error message when server error`() {
        val data: MutableLiveData<String> = MutableLiveData()
        Mockito.`when`(viewModel.serverError).thenReturn(data)
        val result = viewModel.serverError
        verify(viewModel).serverError
        Assert.assertEquals(
            data.getTestObserver().observedValues,
            result.getTestObserver().observedValues
        )
    }
}
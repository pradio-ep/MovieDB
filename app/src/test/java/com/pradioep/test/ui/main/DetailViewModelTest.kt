package com.pradioep.test.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.verify
import com.pradioep.test.model.MovieDetail
import com.pradioep.test.model.MovieReview
import com.pradioep.test.ui.detail.DetailViewModel
import com.pradioep.test.utils.getTestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var viewModel: DetailViewModel

    @Before
    fun start() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `get movie detail by movie id`() {
        doNothing().`when`(viewModel).getMovieDetail(1)
        val data: MutableLiveData<MovieDetail> = MutableLiveData()
        Mockito.`when`(viewModel.movieDetail).thenReturn(data)
        val result = viewModel.movieDetail
        verify(viewModel).movieDetail
        Assert.assertEquals(
            data.getTestObserver().observedValues,
            result.getTestObserver().observedValues
        )
    }

    @Test
    fun `get movie review by movie id`() {
        doNothing().`when`(viewModel).getMovieDetail(1)
        val data: MutableLiveData<ArrayList<MovieReview>> = MutableLiveData()
        Mockito.`when`(viewModel.listMovieReview).thenReturn(data)
        val result = viewModel.listMovieReview
        verify(viewModel).listMovieReview
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
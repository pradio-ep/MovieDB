package com.pradioep.test.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.test.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("popular")
    suspend fun popular(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : NetworkResponse<PopularResponse, Error>
}

open class Repository(private val service: Service) {

    suspend fun popular(api_key: String, language: String, page: Int): NetworkResponse<PopularResponse, Error> {
        return service.popular(api_key, language, page)
    }
}
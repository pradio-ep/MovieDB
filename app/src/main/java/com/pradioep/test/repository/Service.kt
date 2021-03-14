package com.pradioep.test.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.test.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("popular")
    suspend fun popular(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : NetworkResponse<Response, Error>

    @GET("upcoming")
    suspend fun upcoming(
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ) : NetworkResponse<Response, Error>

    @GET("top_rated")
    suspend fun topRated(
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ) : NetworkResponse<Response, Error>

    @GET("now_playing")
    suspend fun nowPlaying(
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ) : NetworkResponse<Response, Error>

    @GET("{movie_id}")
    suspend fun movieDetail(
            @Path("movie_id") movie_id: Int,
            @Query("api_key") api_key: String,
            @Query("language") language: String
    ) : NetworkResponse<MovieDetail, Error>
}

open class Repository(private val service: Service) {

    suspend fun popular(api_key: String, language: String, page: Int): NetworkResponse<Response, Error> {
        return service.popular(api_key, language, page)
    }

    suspend fun upcoming(api_key: String, language: String, page: Int): NetworkResponse<Response, Error> {
        return service.upcoming(api_key, language, page)
    }

    suspend fun topRated(api_key: String, language: String, page: Int): NetworkResponse<Response, Error> {
        return service.topRated(api_key, language, page)
    }

    suspend fun nowPlaying(api_key: String, language: String, page: Int): NetworkResponse<Response, Error> {
        return service.nowPlaying(api_key, language, page)
    }

    suspend fun movieDetail(movie_id: Int, api_key: String, language: String): NetworkResponse<MovieDetail, Error> {
        return service.movieDetail(movie_id, api_key, language)
    }
}
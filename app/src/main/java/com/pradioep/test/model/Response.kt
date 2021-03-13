package com.pradioep.test.model

import com.google.gson.annotations.SerializedName

data class Response (
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: ArrayList<Movie>
)
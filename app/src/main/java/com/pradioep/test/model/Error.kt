package com.pradioep.test.model

import com.google.gson.annotations.SerializedName

data class Error (
    @SerializedName("status")
    val status: Int,
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String
)
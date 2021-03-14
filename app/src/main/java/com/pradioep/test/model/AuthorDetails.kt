package com.pradioep.test.model

import com.google.gson.annotations.SerializedName

data class AuthorDetails (
		@SerializedName("name")
		val name : String,
		@SerializedName("username")
		val username : String,
		@SerializedName("avatar_path")
		val avatar_path : String,
		@SerializedName("rating")
		val rating : Int
)
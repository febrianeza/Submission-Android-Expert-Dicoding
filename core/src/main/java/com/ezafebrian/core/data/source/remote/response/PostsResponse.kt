package com.ezafebrian.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PostsResponse(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<PostsData?>? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null
)

data class PostsData(

	@field:SerializedName("owner")
	val owner: PostsOwner,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("publishDate")
	val publishDate: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("id")
	val postId: String,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null,

	@field:SerializedName("likes")
	val likes: Int? = null
)

data class PostsOwner(

	@field:SerializedName("firstName")
	val firstName: String,

	@field:SerializedName("lastName")
	val lastName: String,

	@field:SerializedName("id")
	val ownerId: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("picture")
	val picture: String,

	@field:SerializedName("email")
	val email: String
)

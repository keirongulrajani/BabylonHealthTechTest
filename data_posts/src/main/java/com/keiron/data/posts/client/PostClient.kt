package com.keiron.data.posts.client

import com.keiron.data.posts.model.CommentDto
import com.keiron.data.posts.model.PostDto
import io.reactivex.Single
import retrofit2.http.GET

interface PostClient {
    @GET("/posts")
    fun getAllPosts(): Single<List<PostDto>>

    @GET("/comments")
    fun getAllComments(): Single<List<CommentDto>>
}
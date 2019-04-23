package com.keiron.data.posts.datasource

import com.keiron.data.posts.client.PostClient
import com.keiron.data.posts.model.CommentDto
import com.keiron.data.posts.model.PostDto
import io.reactivex.Single
import javax.inject.Inject

class PostDataSource @Inject constructor(private val postClient: PostClient) {

    fun getAllPosts(): Single<List<PostDto>> {
        return postClient.getAllPosts()
    }

    fun getAllComments(): Single<List<CommentDto>> {
        return postClient.getAllComments()
    }
}
package com.keiron.babylonhealth.domain.posts.repository

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post
import io.reactivex.Single

interface PostRepository {

    fun getAllPosts(): Single<List<Post>>

    fun getAllComments(): Single<List<Comment>>
}
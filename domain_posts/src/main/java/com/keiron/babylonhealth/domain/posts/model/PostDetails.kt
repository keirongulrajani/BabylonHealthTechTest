package com.keiron.babylonhealth.domain.posts.model

import com.keiron.babylonhealth.domain.accounts.model.User

data class PostDetails(
    val id: Int,
    val title: String,
    val body: String,
    val author: User,
    val comments: List<Comment>
)
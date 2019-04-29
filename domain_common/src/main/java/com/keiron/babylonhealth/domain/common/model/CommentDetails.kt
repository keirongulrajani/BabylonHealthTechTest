package com.keiron.babylonhealth.domain.common.model

data class CommentDetails(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
    val avatarUrl: String
)
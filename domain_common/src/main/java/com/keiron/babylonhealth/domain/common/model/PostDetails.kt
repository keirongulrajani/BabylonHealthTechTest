package com.keiron.babylonhealth.domain.common.model

data class PostDetails(
    val id: Int,
    val title: String,
    val body: String,
    val author: UserDetails,
    val comments: List<CommentDetails>
)
package com.keiron.babylonhealth.domain.posts.creator

import com.keiron.babylonhealth.domain.common.model.CommentDetails
import com.keiron.babylonhealth.domain.posts.model.Comment
import javax.inject.Inject

class CommentDetailsCreator @Inject constructor() {
    fun create(params: Params): CommentDetails {
        val comment = params.comment
        return CommentDetails(
            comment.postId,
            comment.id,
            comment.name,
            comment.email,
            comment.body,
            params.avatarUrl
        )
    }

    data class Params(val comment: Comment, val avatarUrl: String)
}
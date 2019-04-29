package com.keiron.babylonhealth.domain.posts.creator

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.common.model.PostDetails
import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post

import javax.inject.Inject

class PostDetailsCreator @Inject constructor(
    private val userDetailsCreator: UserDetailsCreator,
    private val commentDetailsCreator: CommentDetailsCreator
) {
    fun create(params: Params): PostDetails {
        return PostDetails(
            id = params.post.id,
            title = params.post.title,
            body = params.post.body,
            author = userDetailsCreator.create(UserDetailsCreator.Params(params.authorWithAvatar.first, params.authorWithAvatar.second)),
            comments = params.commentsWithAvatars.map { commentDetailsCreator.create(CommentDetailsCreator.Params(it.first, it.second)) }
        )
    }

    data class Params(val authorWithAvatar: Pair<User, String>, val commentsWithAvatars: List<Pair<Comment, String>>, val post: Post)
}
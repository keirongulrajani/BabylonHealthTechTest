package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.accounts.usecase.GetAllUsersUseCase
import com.keiron.babylonhealth.domain.common.usecase.UseCaseVoid
import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post
import com.keiron.babylonhealth.domain.posts.model.PostDetails
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class GetAllPostsWithDetailsUseCase @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val getAllCommentsUseCase: GetAllCommentsUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase
) : UseCaseVoid<Single<List<PostDetails>>>() {
    override fun buildUseCase(): Single<List<PostDetails>> {
        return Single.zip(
            getAllPostsUseCase.buildUseCase(),
            getAllUsersUseCase.buildUseCase(),
            getAllCommentsUseCase.buildUseCase(),

            Function3<List<Post>, List<User>, List<Comment>, List<PostDetails>> { posts, users, comments ->
                process(posts, users, comments)
            })
    }

    private fun process(
        posts: List<Post>,
        users: List<User>,
        comments: List<Comment>
    ): List<PostDetails> {
        return posts.map {
            PostDetails(
                id = it.id,
                title = it.title,
                body = it.body,
                author = users.first { user -> user.id == it.userId },
                comments = comments.filter { comment -> comment.postId == it.id }
            )
        }
    }
}
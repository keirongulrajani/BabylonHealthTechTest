package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.accounts.usecase.GetAllUsersUseCase
import com.keiron.babylonhealth.domain.accounts.usecase.GetAvatarUrlForEmailUseCase
import com.keiron.babylonhealth.domain.common.model.PostDetails
import com.keiron.babylonhealth.domain.common.usecase.UseCaseVoid
import com.keiron.babylonhealth.domain.posts.creator.PostDetailsCreator
import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.zipWith
import javax.inject.Inject

class GetAllPostsWithDetailsUseCase @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val getAllCommentsUseCase: GetAllCommentsUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getAvatarUrlForEmailUseCase: GetAvatarUrlForEmailUseCase,
    private val postDetailsCreator: PostDetailsCreator
) : UseCaseVoid<Single<List<PostDetails>>>() {
    override fun buildUseCase(): Single<List<PostDetails>> {
        return Single.zip(
            getAllPostsUseCase.buildUseCase(),
            getAllUsersWithAvatarUrl(),
            getAllCommentsWithAvatarUrl(),
            Function3<List<Post>, List<Pair<User, String>>, List<Pair<Comment, String>>, List<PostDetails>> { posts, users, comments ->
                process(posts, users, comments)
            })
    }

    private fun getAllCommentsWithAvatarUrl(): Single<List<Pair<Comment, String>>> {
        return getAllCommentsUseCase.buildUseCase()
            .flattenAsFlowable { it }
            .flatMap { Flowable.just(it).zipWith(getAvatarUrlForEmailUseCase.buildUseCase(it.email).toFlowable()) }
            .toList()
    }

    private fun getAllUsersWithAvatarUrl(): Single<List<Pair<User, String>>> {
        return getAllUsersUseCase.buildUseCase()
            .flattenAsFlowable { it }
            .flatMap { Flowable.just(it).zipWith(getAvatarUrlForEmailUseCase.buildUseCase(it.email).toFlowable()) }
            .toList()
    }

    private fun process(
        posts: List<Post>,
        users: List<Pair<User, String>>,
        comments: List<Pair<Comment, String>>
    ): List<PostDetails> {
        return posts.map {
            val author = getAuthorFromUserList(users, it)
            val commentsForPost = getCommentsForPost(comments, it)
            postDetailsCreator.create(PostDetailsCreator.Params(author, commentsForPost, it))
        }
    }

    private fun getCommentsForPost(
        comments: List<Pair<Comment, String>>,
        it: Post
    ): List<Pair<Comment, String>> {
        return comments.filter { comment -> comment.first.postId == it.id }
    }

    private fun getAuthorFromUserList(
        users: List<Pair<User, String>>,
        it: Post
    ): Pair<User, String> {
        return users.first { user -> user.first.id == it.userId }
    }
}
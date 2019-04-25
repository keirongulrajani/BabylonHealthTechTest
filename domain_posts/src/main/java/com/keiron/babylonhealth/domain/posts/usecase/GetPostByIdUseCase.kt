package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.common.usecase.UseCase
import com.keiron.babylonhealth.domain.posts.exception.NoPostFoundException
import com.keiron.babylonhealth.domain.posts.model.Post
import io.reactivex.Single
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase
) : UseCase<Single<Post>, Int>() {
    override fun buildUseCase(postId: Int): Single<Post> {
        return getAllPostsUseCase.buildUseCase()
            .map { postList -> postList.first { it.id == postId } }
            .onErrorResumeNext {
                if (it is NoSuchElementException) {
                    Single.error(NoPostFoundException(postId))
                } else {
                    Single.error(it)
                }
            }
    }
}
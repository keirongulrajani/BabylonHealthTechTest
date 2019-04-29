package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.common.model.PostDetails
import com.keiron.babylonhealth.domain.common.usecase.UseCase
import com.keiron.babylonhealth.domain.posts.exception.NoPostFoundException
import io.reactivex.Single
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val getAllPostsWithDetailsUseCase: GetAllPostsWithDetailsUseCase
) : UseCase<Single<PostDetails>, Int>() {
    override fun buildUseCase(params: Int): Single<PostDetails> {
        return getAllPostsWithDetailsUseCase.buildUseCase()
            .map { postList -> postList.first { it.id == params } }
            .onErrorResumeNext {
                if (it is NoSuchElementException) {
                    Single.error(NoPostFoundException(params))
                } else {
                    Single.error(it)
                }
            }
    }
}
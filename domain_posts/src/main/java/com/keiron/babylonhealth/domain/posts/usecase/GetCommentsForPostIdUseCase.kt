package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.common.usecase.UseCase
import com.keiron.babylonhealth.domain.posts.model.Comment
import io.reactivex.Single
import javax.inject.Inject

class GetCommentsForPostIdUseCase @Inject constructor(private val getAllCommentsUseCase: GetAllCommentsUseCase) :
    UseCase<Single<List<Comment>>, Int>() {
    override fun buildUseCase(params: Int): Single<List<Comment>> {
        return getAllCommentsUseCase.buildUseCase()
            .flattenAsFlowable { it }
            .filter { it.postId == params }
            .toList()
    }
}
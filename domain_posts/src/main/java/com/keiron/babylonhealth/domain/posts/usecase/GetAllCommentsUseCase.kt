package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.common.usecase.UseCaseVoid
import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllCommentsUseCase @Inject constructor(private val postRepository: PostRepository) :
    UseCaseVoid<Single<List<Comment>>>() {
    override fun buildUseCase(): Single<List<Comment>> {
        return postRepository.getAllComments()
    }
}
package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.common.usecase.UseCaseVoid
import com.keiron.babylonhealth.domain.posts.model.Post
import com.keiron.babylonhealth.domain.posts.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(private val postRepository: PostRepository) :
    UseCaseVoid<Single<List<Post>>>() {
    override fun buildUseCase(): Single<List<Post>> {
        return postRepository.getAllPosts()
    }
}
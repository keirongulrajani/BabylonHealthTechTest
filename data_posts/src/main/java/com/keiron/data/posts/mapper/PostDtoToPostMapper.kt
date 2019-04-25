package com.keiron.data.posts.mapper

import com.keiron.babylonhealth.domain.posts.model.Post
import com.keiron.data.posts.model.PostDto
import com.keiron.library.common.mapper.BaseMapperToDomain
import javax.inject.Inject

class PostDtoToPostMapper @Inject constructor() : BaseMapperToDomain<PostDto, Post>() {
    override fun mapToDomain(toBeTransformed: PostDto): Post {
        return Post(
            userId = toBeTransformed.userId,
            id = toBeTransformed.id,
            title = toBeTransformed.title,
            body = toBeTransformed.body
        )
    }
}
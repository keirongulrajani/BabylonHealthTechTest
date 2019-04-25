package com.keiron.data.posts.mapper

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.data.posts.model.CommentDto
import com.keiron.library.common.mapper.BaseMapperToDomain
import javax.inject.Inject

class CommentDtoToCommentMapper @Inject constructor() : BaseMapperToDomain<CommentDto, Comment>() {
    override fun mapToDomain(toBeTransformed: CommentDto): Comment {
        return Comment(
            toBeTransformed.postId,
            toBeTransformed.id,
            toBeTransformed.name,
            toBeTransformed.email,
            toBeTransformed.body
        )
    }
}
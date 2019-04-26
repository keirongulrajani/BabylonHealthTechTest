package com.keiron.techtest.section.details.mapper

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.library.common.mapper.BaseMapperToPresentation
import com.keiron.techtest.section.details.model.CommentUiModel
import javax.inject.Inject

class CommentToCommentUiModelMapper @Inject constructor() : BaseMapperToPresentation<Comment, CommentUiModel>() {
    override fun mapToPresentation(toBeTransformed: Comment): CommentUiModel {
        return CommentUiModel(
            name = toBeTransformed.name,
            email = toBeTransformed.email,
            body = toBeTransformed.body
        )
    }
}
package com.keiron.techtest.section.details.mapper

import com.keiron.babylonhealth.domain.common.model.CommentDetails
import com.keiron.babylonhealth.ui.components.mapper.ImageUrlToImageUiModelMapper
import com.keiron.library.common.mapper.BaseMapperToPresentation
import com.keiron.techtest.section.details.model.CommentUiModel
import javax.inject.Inject

class CommentToCommentUiModelMapper @Inject constructor(private val imageUrlToImageUiModelMapper: ImageUrlToImageUiModelMapper) :
    BaseMapperToPresentation<CommentDetails, CommentUiModel>() {
    override fun mapToPresentation(toBeTransformed: CommentDetails): CommentUiModel {
        return CommentUiModel(
            name = toBeTransformed.name,
            email = toBeTransformed.email,
            body = toBeTransformed.body,
            avatar = imageUrlToImageUiModelMapper.mapToPresentation(toBeTransformed.avatarUrl)
        )
    }
}
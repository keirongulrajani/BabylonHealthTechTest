package com.keiron.techtest.section.main.mapper

import com.keiron.babylonhealth.domain.common.model.PostDetails
import com.keiron.babylonhealth.ui.components.mapper.ImageUrlToImageUiModelMapper
import com.keiron.library.common.mapper.BaseMapperToPresentation
import com.keiron.techtest.section.main.model.MainUiModel
import javax.inject.Inject

class PostDetailsToMainUiModelMapper @Inject constructor(private val imageUrlToImageUiModelMapper: ImageUrlToImageUiModelMapper) :
    BaseMapperToPresentation<PostDetails, MainUiModel>() {
    override fun mapToPresentation(toBeTransformed: PostDetails): MainUiModel {
        return MainUiModel(
            id = toBeTransformed.id,
            postTitle = toBeTransformed.title,
            authorTitle = toBeTransformed.author.username,
            authorAvatar = imageUrlToImageUiModelMapper.mapToPresentation(toBeTransformed.author.avatarUrl),
            numberOfComments = toBeTransformed.comments.size
        )
    }
}
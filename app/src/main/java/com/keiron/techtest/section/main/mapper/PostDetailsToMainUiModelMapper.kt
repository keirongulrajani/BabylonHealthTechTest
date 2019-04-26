package com.keiron.techtest.section.main.mapper

import com.keiron.babylonhealth.domain.posts.model.PostDetails
import com.keiron.library.common.mapper.BaseMapperToPresentation
import com.keiron.techtest.section.main.model.MainUiModel
import javax.inject.Inject

class PostDetailsToMainUiModelMapper @Inject constructor() : BaseMapperToPresentation<PostDetails, MainUiModel>() {
    override fun mapToPresentation(toBeTransformed: PostDetails): MainUiModel {
        return MainUiModel(
            id = toBeTransformed.id,
            postTitle = toBeTransformed.title,
            authorTitle = toBeTransformed.author.username,
            numberOfComments = toBeTransformed.comments.size
        )
    }
}
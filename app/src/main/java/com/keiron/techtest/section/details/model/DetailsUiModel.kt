package com.keiron.techtest.section.details.model

import com.keiron.babylonhealth.ui.components.model.ImageUiModel

class DetailsUiModel(
    val postTitle: String,
    val body: String,
    val authorTitle: String,
    val authorAvatar: ImageUiModel,
    val comments: List<CommentUiModel>
)
package com.keiron.techtest.section.main.model

import com.keiron.babylonhealth.ui.components.model.ImageUiModel

class MainUiModel(
    val id: Int,
    val postTitle: String,
    val authorTitle: String,
    val authorAvatar: ImageUiModel,
    val numberOfComments: Int
)
package com.keiron.babylonhealth.ui.components.mapper

import com.keiron.babylonhealth.ui.components.model.ImageUiModel
import com.keiron.library.common.mapper.BaseMapperToPresentation
import javax.inject.Inject

class ImageUrlToImageUiModelMapper @Inject constructor() : BaseMapperToPresentation<String, ImageUiModel>() {
    override fun mapToPresentation(toBeTransformed: String): ImageUiModel {
        return ImageUiModel(toBeTransformed)
    }

}
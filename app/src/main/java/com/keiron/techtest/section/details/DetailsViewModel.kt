package com.keiron.techtest.section.details

import com.keiron.babylonhealth.domain.posts.usecase.GetAllPostsUseCase
import com.keiron.babylonhealth.ui.components.viewmodel.BaseViewModel
import com.keiron.library.common.schedulers.SchedulersProvider
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val schedulersProvider: SchedulersProvider
) : BaseViewModel()
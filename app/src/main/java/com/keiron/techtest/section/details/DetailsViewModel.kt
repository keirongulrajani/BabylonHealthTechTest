package com.keiron.techtest.section.details

import androidx.lifecycle.MutableLiveData
import com.keiron.babylonhealth.domain.posts.exception.NoPostFoundException
import com.keiron.babylonhealth.domain.posts.usecase.GetPostByIdUseCase
import com.keiron.babylonhealth.ui.components.viewmodel.BaseViewModel
import com.keiron.library.common.schedulers.SchedulersProvider
import com.keiron.techtest.section.details.mapper.PostDetailsToDetailsUiModelMapper
import com.keiron.techtest.section.details.model.DetailsUiModel
import com.keiron.techtest.section.details.model.DetailsViewState
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getPostByIdUseCase: GetPostByIdUseCase,
    private val schedulersProvider: SchedulersProvider,
    private val postDetailsToDetailsUiModelMapper: PostDetailsToDetailsUiModelMapper
) : BaseViewModel() {

    internal val detailsViewState = MutableLiveData<DetailsViewState>()

    fun onDetailsPageCreatedForPost(postId: Int) {
        getPostByIdUseCase.buildUseCase(postId)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())
            .doOnSubscribe { detailsViewState.postValue(createLoadingState()) }
            .map { postDetailsToDetailsUiModelMapper.mapToPresentation(it) }
            .subscribe({
                detailsViewState.postValue(createDataState(it))
            }, {
                detailsViewState.postValue(createErrorState(it))
            })
            .addTo(compositeDisposable)
    }

    private fun createLoadingState(): DetailsViewState = DetailsViewState(true, DetailsViewState.Error.None, null)

    private fun createErrorState(throwable: Throwable): DetailsViewState {
        val error = when (throwable) {
            is NoPostFoundException -> DetailsViewState.Error.NoPostFound(throwable.id)
            else -> DetailsViewState.Error.NetworkIssue("Error fetching post $throwable")
        }

        return DetailsViewState(false, error, null)
    }

    private fun createDataState(uiModel: DetailsUiModel): DetailsViewState =
        DetailsViewState(false, DetailsViewState.Error.None, uiModel)
}
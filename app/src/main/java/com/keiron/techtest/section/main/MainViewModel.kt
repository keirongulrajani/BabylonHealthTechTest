package com.keiron.techtest.section.main

import androidx.lifecycle.MutableLiveData
import com.keiron.babylonhealth.domain.posts.usecase.GetAllPostsWithDetailsUseCase
import com.keiron.babylonhealth.ui.components.viewmodel.BaseViewModel
import com.keiron.library.common.schedulers.SchedulersProvider
import com.keiron.techtest.section.main.mapper.PostDetailsToMainUiModelMapper
import com.keiron.techtest.section.main.model.MainUiModel
import com.keiron.techtest.section.main.model.MainViewState
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllPostsWithDetailsUseCase: GetAllPostsWithDetailsUseCase,
    private val schedulersProvider: SchedulersProvider,
    private val postDetailsToMainUiModelMapper: PostDetailsToMainUiModelMapper
) : BaseViewModel() {

    internal val mainViewState = MutableLiveData<MainViewState>()

    fun onMainPageCreated() {
        getAllPostsWithDetailsUseCase.buildUseCase()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())
            .doOnSubscribe { mainViewState.postValue(createLoadingState()) }
            .map { postDetailsToMainUiModelMapper.mapToPresentation(it) }
            .subscribe({
                mainViewState.postValue(createDataState(it))
            }, {
                mainViewState.postValue(createErrorState(it))
            })
            .addTo(compositeDisposable)
    }

    private fun createLoadingState(): MainViewState = MainViewState(true, MainViewState.Error.None, emptyList())

    private fun createErrorState(throwable: Throwable): MainViewState {
        return MainViewState(false, MainViewState.Error.NetworkIssue("Error fetching posts: $throwable"), emptyList())
    }

    private fun createDataState(uiModels: List<MainUiModel>): MainViewState =
        MainViewState(false, MainViewState.Error.None, uiModels)
}
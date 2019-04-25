package com.keiron.techtest.section.main

import com.keiron.babylonhealth.domain.posts.usecase.GetAllPostsUseCase
import com.keiron.babylonhealth.ui.components.viewmodel.BaseViewModel
import com.keiron.library.common.schedulers.SchedulersProvider
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val schedulersProvider: SchedulersProvider
) : BaseViewModel() {

    fun onMainPageCreated() {
        getAllPostsUseCase.buildUseCase()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())
            .subscribe({
                System.out.println("Got back this list: $it")
            }, {
                System.out.println("Got back this error: $it")
            })
            .addTo(compositeDisposable)
    }
}
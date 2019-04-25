package com.keiron.babylonhealth.ui.components.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    public override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

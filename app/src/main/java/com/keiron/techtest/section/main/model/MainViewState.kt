package com.keiron.techtest.section.main.model

data class MainViewState(
    val loading: Boolean,
    val error: Error,
    val mainUiModels: List<MainUiModel>
) {
    sealed class Error {
        object None : Error()
        data class NetworkIssue(val title: String) : Error()
    }
}
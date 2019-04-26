package com.keiron.techtest.section.details.model

data class DetailsViewState(
    val loading: Boolean,
    val error: Error,
    val detailsUiModel: DetailsUiModel?
) {
    sealed class Error {
        object None : Error()
        data class NoPostFound(val id: Int) : Error()
        data class NetworkIssue(val title: String) : Error()
    }
}
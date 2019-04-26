package com.keiron.techtest.section.details.model

data class DetailsViewState(
    val loading: Boolean,
    val error: Error,
    val detailsUiModel: DetailsUiModel?
) {
    sealed class Error {
        object None : Error()
        object NoPostFound : Error()
        data class NetworkIssue(val title: String) : Error()
    }
}
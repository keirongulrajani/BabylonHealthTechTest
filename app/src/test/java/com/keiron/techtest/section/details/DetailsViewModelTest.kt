package com.keiron.techtest.section.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.keiron.babylonhealth.domain.common.model.PostDetails
import com.keiron.babylonhealth.domain.posts.exception.NoPostFoundException
import com.keiron.babylonhealth.domain.posts.usecase.GetPostByIdUseCase
import com.keiron.library.common.schedulers.SchedulersProvider
import com.keiron.techtest.section.details.mapper.PostDetailsToDetailsUiModelMapper
import com.keiron.techtest.section.details.model.DetailsUiModel
import com.keiron.techtest.section.details.model.DetailsViewState
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DetailsViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getPostByIdUseCase: GetPostByIdUseCase
    @Mock
    private lateinit var schedulersProvider: SchedulersProvider
    @Mock
    private lateinit var postDetailsToDetailsUiModelMapper: PostDetailsToDetailsUiModelMapper

    @Mock
    private lateinit var detailsViewStateObserver: Observer<DetailsViewState>
    private lateinit var classUnderTest: DetailsViewModel

    @Before
    fun setUp() {
        initMocks(this)

        whenever(schedulersProvider.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulersProvider.mainThread()).thenReturn(Schedulers.trampoline())
        classUnderTest = DetailsViewModel(
            getPostByIdUseCase,
            schedulersProvider,
            postDetailsToDetailsUiModelMapper
        )

        classUnderTest.detailsViewState.observeForever(detailsViewStateObserver)
    }

    @Test
    fun `given post id and posts returned from getPostByIdUseCase when onDetailsPageCreatedForPost then use case called and results mapped to ui `() {
        // Given
        val postId = 123
        val post = mock<PostDetails>()
        whenever(getPostByIdUseCase.buildUseCase(postId)).thenReturn(Single.just(post))
        val uiModel = mock<DetailsUiModel>()
        whenever(postDetailsToDetailsUiModelMapper.mapToPresentation(post)).thenReturn(uiModel)

        // When
        classUnderTest.onDetailsPageCreatedForPost(postId)

        // Then
        verify(getPostByIdUseCase).buildUseCase(postId)
        verify(postDetailsToDetailsUiModelMapper).mapToPresentation(post)
    }

    @Test
    fun `given successful data load when onDetailsPageCreatedForPost then observe loading and success view states`() {
        // Given
        val postId = 123
        val post = mock<PostDetails>()
        whenever(getPostByIdUseCase.buildUseCase(postId)).thenReturn(Single.just(post))
        val expectedUiModel = mock<DetailsUiModel>()
        whenever(postDetailsToDetailsUiModelMapper.mapToPresentation(post)).thenReturn(expectedUiModel)

        // When
        classUnderTest.onDetailsPageCreatedForPost(postId)

        // Then
        val argumentCaptor = ArgumentCaptor.forClass(DetailsViewState::class.java)
        verify(detailsViewStateObserver, times(2)).onChanged(argumentCaptor.capture())

        val viewStateLoading = argumentCaptor.allValues[0]
        assertEquals(true, viewStateLoading.loading)
        assertEquals(DetailsViewState.Error.None, viewStateLoading.error)
        assertNull(viewStateLoading.detailsUiModel)

        val viewStateSuccess = argumentCaptor.allValues[1]
        assertEquals(false, viewStateSuccess.loading)
        assertEquals(DetailsViewState.Error.None, viewStateSuccess.error)
        assertEquals(expectedUiModel, viewStateSuccess.detailsUiModel)
    }

    @Test
    fun `given unsuccessful data load when onDetailsPageCreatedForPost then observe loading and error view states`() {
        // Given
        val postId = 123
        whenever(getPostByIdUseCase.buildUseCase(postId)).thenReturn(Single.error(Throwable()))

        // When
        classUnderTest.onDetailsPageCreatedForPost(postId)

        // Then
        verify(postDetailsToDetailsUiModelMapper, never()).mapToPresentation(any<List<PostDetails>>())

        val argumentCaptor = ArgumentCaptor.forClass(DetailsViewState::class.java)
        verify(detailsViewStateObserver, times(2)).onChanged(argumentCaptor.capture())

        val viewStateLoading = argumentCaptor.allValues[0]
        assertEquals(true, viewStateLoading.loading)
        assertEquals(DetailsViewState.Error.None, viewStateLoading.error)
        assertNull(viewStateLoading.detailsUiModel)

        val viewStateSuccess = argumentCaptor.allValues[1]
        assertEquals(false, viewStateSuccess.loading)
        assertTrue(viewStateSuccess.error is DetailsViewState.Error.NetworkIssue)
        assertNull(viewStateLoading.detailsUiModel)
    }

    @Test
    fun `given post not found when onDetailsPageCreatedForPost then observe loading and error view states`() {
        // Given
        val postId = 123
        whenever(getPostByIdUseCase.buildUseCase(postId)).thenReturn(Single.error(NoPostFoundException(postId)))

        // When
        classUnderTest.onDetailsPageCreatedForPost(postId)

        // Then
        verify(postDetailsToDetailsUiModelMapper, never()).mapToPresentation(any<List<PostDetails>>())

        val argumentCaptor = ArgumentCaptor.forClass(DetailsViewState::class.java)
        verify(detailsViewStateObserver, times(2)).onChanged(argumentCaptor.capture())

        val viewStateLoading = argumentCaptor.allValues[0]
        assertEquals(true, viewStateLoading.loading)
        assertEquals(DetailsViewState.Error.None, viewStateLoading.error)
        assertNull(viewStateLoading.detailsUiModel)

        val viewStateSuccess = argumentCaptor.allValues[1]
        assertEquals(false, viewStateSuccess.loading)
        assertEquals(DetailsViewState.Error.NoPostFound(postId), viewStateSuccess.error)
        assertNull(viewStateLoading.detailsUiModel)
    }
}
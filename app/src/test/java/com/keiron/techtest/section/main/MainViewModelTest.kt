package com.keiron.techtest.section.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.keiron.babylonhealth.domain.posts.model.PostDetails
import com.keiron.babylonhealth.domain.posts.usecase.GetAllPostsWithDetailsUseCase
import com.keiron.library.common.schedulers.SchedulersProvider
import com.keiron.techtest.section.main.mapper.PostDetailsToMainUiModelMapper
import com.keiron.techtest.section.main.model.MainUiModel
import com.keiron.techtest.section.main.model.MainViewState
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
import kotlin.test.assertTrue

class MainViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getAllPostsWithDetailsUseCase: GetAllPostsWithDetailsUseCase
    @Mock
    private lateinit var schedulersProvider: SchedulersProvider
    @Mock
    private lateinit var postDetailsToMainUiModelMapper: PostDetailsToMainUiModelMapper

    @Mock
    private lateinit var mainViewStateObserver: Observer<MainViewState>
    private lateinit var classUnderTest: MainViewModel

    @Before
    fun setUp() {
        initMocks(this)

        whenever(schedulersProvider.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulersProvider.mainThread()).thenReturn(Schedulers.trampoline())
        classUnderTest = MainViewModel(
            getAllPostsWithDetailsUseCase,
            schedulersProvider,
            postDetailsToMainUiModelMapper
        )

        classUnderTest.mainViewState.observeForever(mainViewStateObserver)
    }

    @Test
    fun `given posts returned from getAllPostsWithDetailsUseCase when onMainPageCreated then use case called and results mapped to ui `() {
        // Given
        val postDetailsList = listOf<PostDetails>(mock())
        whenever(getAllPostsWithDetailsUseCase.buildUseCase()).thenReturn(Single.just(postDetailsList))
        val uiModelList = listOf<MainUiModel>(mock())
        whenever(postDetailsToMainUiModelMapper.mapToPresentation(postDetailsList)).thenReturn(uiModelList)

        // When
        classUnderTest.onMainPageCreated()

        // Then
        verify(getAllPostsWithDetailsUseCase).buildUseCase()
        verify(postDetailsToMainUiModelMapper).mapToPresentation(postDetailsList)
    }

    @Test
    fun `given successful data load when onMainPageCreated then observe loading and success view states`() {
        // Given
        val postDetailsList = listOf<PostDetails>(mock())
        whenever(getAllPostsWithDetailsUseCase.buildUseCase()).thenReturn(Single.just(postDetailsList))
        val expectedUiModelList = listOf<MainUiModel>(mock())
        whenever(postDetailsToMainUiModelMapper.mapToPresentation(postDetailsList)).thenReturn(expectedUiModelList)

        // When
        classUnderTest.onMainPageCreated()

        // Then
        val argumentCaptor = ArgumentCaptor.forClass(MainViewState::class.java)
        verify(mainViewStateObserver, times(2)).onChanged(argumentCaptor.capture())

        val viewStateLoading = argumentCaptor.allValues[0]
        assertEquals(true, viewStateLoading.loading)
        assertEquals(MainViewState.Error.None, viewStateLoading.error)
        assertEquals(emptyList(), viewStateLoading.mainUiModels)

        val viewStateSuccess = argumentCaptor.allValues[1]
        assertEquals(false, viewStateSuccess.loading)
        assertEquals(MainViewState.Error.None, viewStateSuccess.error)
        assertEquals(expectedUiModelList, viewStateSuccess.mainUiModels)
    }

    @Test
    fun `given unsuccessful data load when onMainPageCreated then observe loading and error view states`() {
        // Given
        whenever(getAllPostsWithDetailsUseCase.buildUseCase()).thenReturn(Single.error(Throwable()))

        // When
        classUnderTest.onMainPageCreated()

        // Then
        verify(postDetailsToMainUiModelMapper, never()).mapToPresentation(any<List<PostDetails>>())

        val argumentCaptor = ArgumentCaptor.forClass(MainViewState::class.java)
        verify(mainViewStateObserver, times(2)).onChanged(argumentCaptor.capture())

        val viewStateLoading = argumentCaptor.allValues[0]
        assertEquals(true, viewStateLoading.loading)
        assertEquals(MainViewState.Error.None, viewStateLoading.error)
        assertEquals(emptyList(), viewStateLoading.mainUiModels)

        val viewStateSuccess = argumentCaptor.allValues[1]
        assertEquals(false, viewStateSuccess.loading)
        assertTrue(viewStateSuccess.error is MainViewState.Error.NetworkIssue)
        assertEquals(emptyList(), viewStateSuccess.mainUiModels)
    }
}
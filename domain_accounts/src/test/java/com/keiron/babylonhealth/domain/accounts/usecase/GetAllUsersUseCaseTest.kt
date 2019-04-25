package com.keiron.babylonhealth.domain.accounts.usecase

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.accounts.repository.AccountRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class GetAllUsersUseCaseTest {
    @Mock
    private lateinit var accountRepository: AccountRepository

    private lateinit var classUnderTest: GetAllUsersUseCase
    @Before
    fun setUp() {
        initMocks(this)

        classUnderTest = GetAllUsersUseCase(accountRepository)
    }

    @Test
    fun `given user accounts when build use case then user accounts fetched from account repository and returned`() {
        // Given
        val expectedUserList = listOf(mock<User>())
        whenever(accountRepository.getAllUserAccounts()).thenReturn(Single.just(expectedUserList))

        // When
        val testObserver = classUnderTest.buildUseCase().test()

        // Then
        verify(accountRepository).getAllUserAccounts()

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(expectedUserList)
    }
}
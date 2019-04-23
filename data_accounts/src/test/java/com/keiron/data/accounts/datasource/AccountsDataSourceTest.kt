package com.keiron.data.accounts.datasource

import com.keiron.data.accounts.client.ProfileClient
import com.keiron.data.accounts.model.UserDto
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class AccountsDataSourceTest {

    @Mock
    private lateinit var profileClient: ProfileClient

    private lateinit var classUnderTest: AccountsDataSource

    @Before
    fun setUp() {
        initMocks(this)
        whenever(profileClient.getUsers()).thenReturn(Single.just(emptyList()))

        classUnderTest = AccountsDataSource(profileClient)
    }

    @Test
    fun `when getAllUserAccounts then user accounts fetched from client`() {
        // When
        classUnderTest.getAllUserAccounts()

        // Then
        verify(profileClient).getUsers()
    }

    @Test
    fun `given list of user accounts from client when getAllUserAccounts then user accounts from client returned`() {
        // Given
        val userDto = mock<UserDto>()
        val expectedData = listOf(userDto)
        whenever(profileClient.getUsers()).thenReturn(Single.just(expectedData))

        // When
        val testObserver = classUnderTest.getAllUserAccounts().test()

        // Then
        testObserver.assertNoErrors()
            .assertComplete()
            .assertValue(expectedData)
    }
}
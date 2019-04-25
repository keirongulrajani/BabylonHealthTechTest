package com.keiron.data.accounts.repository

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.data.accounts.datasource.AccountsDataSource
import com.keiron.data.accounts.mapper.UserDtoToUserMapper
import com.keiron.data.accounts.model.UserDto
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AccountRepositoryImplTest {

    @Mock
    private lateinit var accountsDataSource: AccountsDataSource
    @Mock
    private lateinit var userDtoToUserMapper: UserDtoToUserMapper

    private lateinit var classUnderTest: AccountRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = AccountRepositoryImpl(accountsDataSource, userDtoToUserMapper)
    }

    @Test
    fun `given user accounts list when getAllUserAccounts then returns mapped user list`() {
        // Given
        val userList = listOf(mock<UserDto>())
        val mappedUserList = listOf(mock<User>())
        whenever(accountsDataSource.getAllUserAccounts()).thenReturn(Single.just(userList))
        whenever(userDtoToUserMapper.mapToDomain(userList)).thenReturn(mappedUserList)

        // When
        val testObserver = classUnderTest.getAllUserAccounts().test()

        // Then
        verify(accountsDataSource).getAllUserAccounts()
        verify(userDtoToUserMapper).mapToDomain(userList)
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(mappedUserList)
    }
}
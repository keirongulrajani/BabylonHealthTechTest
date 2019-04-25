package com.keiron.data.accounts.repository

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.accounts.repository.AccountRepository
import com.keiron.data.accounts.datasource.AccountsDataSource
import com.keiron.data.accounts.mapper.UserDtoToUserMapper
import io.reactivex.Single
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountsDataSource: AccountsDataSource,
    private val userDtoToUserMapper: UserDtoToUserMapper
) : AccountRepository {
    override fun getAllUserAccounts(): Single<List<User>> {
        return accountsDataSource.getAllUserAccounts()
            .map { userDtoToUserMapper.mapToDomain(it) }
    }
}
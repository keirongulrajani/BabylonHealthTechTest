package com.keiron.data.accounts.datasource

import com.keiron.data.accounts.client.ProfileClient
import com.keiron.data.accounts.model.UserDto
import io.reactivex.Single
import javax.inject.Inject

class AccountsDataSource @Inject constructor(private val profileClient: ProfileClient) {

    fun getAllUserAccounts(): Single<List<UserDto>> {
        return profileClient.getUsers()
    }
}
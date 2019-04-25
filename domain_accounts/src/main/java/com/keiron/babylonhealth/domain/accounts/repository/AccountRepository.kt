package com.keiron.babylonhealth.domain.accounts.repository

import com.keiron.babylonhealth.domain.accounts.model.User
import io.reactivex.Single

interface AccountRepository {

    fun getAllUserAccounts(): Single<List<User>>

}
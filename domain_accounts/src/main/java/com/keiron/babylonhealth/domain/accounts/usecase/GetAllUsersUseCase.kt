package com.keiron.babylonhealth.domain.accounts.usecase

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.accounts.repository.AccountRepository
import com.keiron.babylonhealth.domain.common.usecase.UseCaseVoid
import io.reactivex.Single
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val accountRepository: AccountRepository) :

    UseCaseVoid<Single<List<User>>>() {
    override fun buildUseCase(): Single<List<User>> {
        return accountRepository.getAllUserAccounts()
    }
}

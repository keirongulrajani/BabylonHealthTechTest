package com.keiron.babylonhealth.domain.accounts.usecase

import com.keiron.babylonhealth.domain.common.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

const val BASE_URL = "https://api.adorable.io/avatars/285/%s.png"

class GetAvatarUrlForEmailUseCase @Inject constructor() : UseCase<Single<String>, String>() {
    override fun buildUseCase(params: String): Single<String> {
        return Single.just(String.format(BASE_URL, params))
    }
}
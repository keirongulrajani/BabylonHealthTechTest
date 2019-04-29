package com.keiron.babylonhealth.domain.posts.creator

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.common.model.UserDetails
import javax.inject.Inject

class UserDetailsCreator @Inject constructor() {
    fun create(params: Params): UserDetails {
        val user = params.user
        return UserDetails(
            user.id,
            user.name,
            user.username,
            user.email,
            user.phone,
            user.website,
            params.avatarUrl
        )
    }

    data class Params(val user: User, val avatarUrl: String)
}
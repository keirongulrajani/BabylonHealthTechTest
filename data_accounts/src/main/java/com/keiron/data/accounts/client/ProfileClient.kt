package com.keiron.data.accounts.client

import com.keiron.data.accounts.model.UserDto
import io.reactivex.Single
import retrofit2.http.GET

interface ProfileClient {
    @GET("/users")
    fun getUsers(): Single<List<UserDto>>
}
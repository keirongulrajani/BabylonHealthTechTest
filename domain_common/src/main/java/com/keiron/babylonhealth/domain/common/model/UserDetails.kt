package com.keiron.babylonhealth.domain.common.model

data class UserDetails(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    val avatarUrl: String
)
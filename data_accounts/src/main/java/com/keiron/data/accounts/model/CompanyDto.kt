package com.keiron.data.accounts.model

import com.google.gson.annotations.SerializedName

data class CompanyDto(
    @SerializedName("name") val name: String,
    @SerializedName("catchPhrase") val catchPhrase: String,
    @SerializedName("bs") val businessDescription: String
)
package com.keiron.data.accounts.model

import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName("street") val street: String,
    @SerializedName("suite") val suite: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipcode: String,
    @SerializedName("geo") val geoLocation: GeoDto
)

data class GeoDto(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double
)
package com.keiron.babylonhealth.domain.accounts.model

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geoLocation: GeoLocation
)

data class GeoLocation(
    val latitude: Double,
    val longitude: Double
)
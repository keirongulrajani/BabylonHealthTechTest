package com.keiron.data.accounts.mapper

import com.keiron.babylonhealth.domain.accounts.model.Address
import com.keiron.babylonhealth.domain.accounts.model.GeoLocation
import com.keiron.data.accounts.model.AddressDto
import com.keiron.data.accounts.model.GeoDto
import com.keiron.library.common.mapper.BaseMapperToDomain
import javax.inject.Inject

class AddressDtoToAddressMapper @Inject constructor() : BaseMapperToDomain<AddressDto, Address>() {
    override fun mapToDomain(toBeTransformed: AddressDto): Address {
        return Address(
            toBeTransformed.street,
            toBeTransformed.suite,
            toBeTransformed.city,
            toBeTransformed.zipcode,
            getGeoLocation(toBeTransformed.geoLocation)
        )
    }

    private fun getGeoLocation(geoLocation: GeoDto): GeoLocation {
        return GeoLocation(geoLocation.latitude, geoLocation.longitude)
    }
}
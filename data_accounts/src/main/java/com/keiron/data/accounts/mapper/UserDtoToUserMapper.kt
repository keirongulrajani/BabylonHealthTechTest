package com.keiron.data.accounts.mapper

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.data.accounts.model.UserDto
import com.keiron.library.common.mapper.BaseMapperToDomain
import javax.inject.Inject

class UserDtoToUserMapper @Inject constructor(
    private val addressDtoToAddressMapper: AddressDtoToAddressMapper,
    private val companyDtoToCompanyMapper: CompanyDtoToCompanyMapper
) :
    BaseMapperToDomain<UserDto, User>() {

    override fun mapToDomain(toBeTransformed: UserDto): User {
        return User(
            toBeTransformed.id,
            toBeTransformed.name,
            toBeTransformed.username,
            toBeTransformed.email,
            addressDtoToAddressMapper.mapToDomain(toBeTransformed.address),
            toBeTransformed.phone,
            toBeTransformed.website,
            companyDtoToCompanyMapper.mapToDomain(toBeTransformed.company)
        )
    }

}
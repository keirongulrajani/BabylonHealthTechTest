package com.keiron.data.accounts.mapper

import com.keiron.babylonhealth.domain.accounts.model.Company
import com.keiron.data.accounts.model.CompanyDto
import com.keiron.library.common.mapper.BaseMapperToDomain
import javax.inject.Inject

class CompanyDtoToCompanyMapper @Inject constructor() : BaseMapperToDomain<CompanyDto, Company>() {
    override fun mapToDomain(toBeTransformed: CompanyDto): Company {
        return Company(
            name = toBeTransformed.name,
            catchPhrase = toBeTransformed.catchPhrase,
            businessDescription = toBeTransformed.businessDescription
        )
    }
}
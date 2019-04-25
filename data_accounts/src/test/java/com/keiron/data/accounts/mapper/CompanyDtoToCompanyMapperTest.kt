package com.keiron.data.accounts.mapper

import com.keiron.data.accounts.model.CompanyDto
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test

class CompanyDtoToCompanyMapperTest {

    private lateinit var classUnderTest: CompanyDtoToCompanyMapper

    @Before
    fun setUp() {
        classUnderTest = CompanyDtoToCompanyMapper()
    }

    @Test
    fun `given company dto when mapToDomain then maps to company`() {
        // Given
        val expectedName = "name"
        val expectedCatchPhrase = "catchphrase"
        val expectedDescription = "description"
        val companyDto = mock<CompanyDto> {
            on { name } doReturn expectedName
            on { catchPhrase } doReturn expectedCatchPhrase
            on { businessDescription } doReturn expectedDescription
        }

        // When
        val returnedCompany = classUnderTest.mapToDomain(companyDto)

        // Then
        with(returnedCompany) {
            kotlin.test.assertEquals(expectedName, name)
            kotlin.test.assertEquals(expectedCatchPhrase, catchPhrase)
            kotlin.test.assertEquals(expectedDescription, businessDescription)
        }
    }
}
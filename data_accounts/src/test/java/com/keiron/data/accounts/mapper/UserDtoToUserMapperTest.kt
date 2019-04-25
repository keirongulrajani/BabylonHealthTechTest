package com.keiron.data.accounts.mapper

import com.keiron.babylonhealth.domain.accounts.model.Address
import com.keiron.babylonhealth.domain.accounts.model.Company
import com.keiron.data.accounts.model.AddressDto
import com.keiron.data.accounts.model.CompanyDto
import com.keiron.data.accounts.model.UserDto
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import kotlin.test.assertEquals

class UserDtoToUserMapperTest {

    @Mock
    private lateinit var addressDtoToAddressMapper: AddressDtoToAddressMapper
    @Mock
    private lateinit var companyDtoToCompanyMapper: CompanyDtoToCompanyMapper

    private lateinit var classUnderTest: UserDtoToUserMapper

    @Before
    fun setUp() {
        initMocks(this)
        classUnderTest = UserDtoToUserMapper(addressDtoToAddressMapper, companyDtoToCompanyMapper)
    }

    @Test
    fun `given user dto when mapToDomain then maps to user`() {
        // Given
        val addressDto = mock<AddressDto>()
        val companyDto = mock<CompanyDto>()
        val expectedAddress = mock<Address>()
        whenever(addressDtoToAddressMapper.mapToDomain(addressDto)).thenReturn(expectedAddress)
        val expectedCompany = mock<Company>()
        whenever(companyDtoToCompanyMapper.mapToDomain(companyDto)).thenReturn(expectedCompany)
        val expectedName = "name"
        val expectedUsername = "username"
        val expectedEmail = "email"
        val expectedPhone = "phone"
        val expectedWebsite = "website"
        val expectedId = 123
        val userDto = mock<UserDto> {
            on { id } doReturn expectedId
            on { name } doReturn expectedName
            on { username } doReturn expectedUsername
            on { email } doReturn expectedEmail
            on { phone } doReturn expectedPhone
            on { website } doReturn expectedWebsite
            on { company } doReturn companyDto
            on { address } doReturn addressDto
        }

        // When
        val returnedUser = classUnderTest.mapToDomain(userDto)

        // Then
        with(returnedUser) {
            assertEquals(expectedId, id)
            assertEquals(expectedName, name)
            assertEquals(expectedUsername, username)
            assertEquals(expectedEmail, email)
            assertEquals(expectedPhone, phone)
            assertEquals(expectedWebsite, website)
            assertEquals(expectedCompany, company)
            assertEquals(expectedAddress, address)
        }
    }
}
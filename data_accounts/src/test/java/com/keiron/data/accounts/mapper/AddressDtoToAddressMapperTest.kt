package com.keiron.data.accounts.mapper

import com.keiron.data.accounts.model.AddressDto
import com.keiron.data.accounts.model.GeoDto
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class AddressDtoToAddressMapperTest {
    private lateinit var classUnderTest: AddressDtoToAddressMapper

    @Before
    fun setUp() {
        classUnderTest = AddressDtoToAddressMapper()
    }

    @Test
    fun `given address dto when mapToDomain then maps to Address`() {
        // Given
        val expectedLatitude = 123.toDouble()
        val expectedLongitude = 456.toDouble()
        val mockGeo = mock<GeoDto> {
            on { latitude } doReturn expectedLatitude
            on { longitude } doReturn expectedLongitude
        }
        val expectedStreet = "street"
        val expectedSuite = "suite"
        val expectedCity = "city"
        val expectedZipcode = "zipcode"
        val addressDto = mock<AddressDto> {
            on { street } doReturn expectedStreet
            on { suite } doReturn expectedSuite
            on { city } doReturn expectedCity
            on { zipcode } doReturn expectedZipcode
            on { geoLocation } doReturn mockGeo
        }

        // When
        val returnedAddress = classUnderTest.mapToDomain(addressDto)

        // Then
        with(returnedAddress) {
            assertEquals(expectedStreet, street)
            assertEquals(expectedSuite, suite)
            assertEquals(expectedCity, city)
            assertEquals(expectedZipcode, zipcode)
            with(geoLocation) {
                assertEquals(expectedLatitude, latitude)
                assertEquals(expectedLongitude, longitude)
            }
        }
    }
}
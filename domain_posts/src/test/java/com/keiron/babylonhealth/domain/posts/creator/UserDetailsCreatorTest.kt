package com.keiron.babylonhealth.domain.posts.creator

import com.keiron.babylonhealth.domain.accounts.model.User
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class UserDetailsCreatorTest {

    private lateinit var classUnderTest: UserDetailsCreator
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = UserDetailsCreator()
    }

    @Test
    fun `given user and avatar url when create then UserDetails returned`() {
        // Given
        val expectedName = "name"
        val expectedUsername = "username"
        val expectedEmail = "email"
        val expectedPhone = "phone"
        val expectedWebsite = "website"
        val expectedId = 123
        val expectedUser = mock<User> {
            on { id } doReturn expectedId
            on { name } doReturn expectedName
            on { username } doReturn expectedUsername
            on { email } doReturn expectedEmail
            on { phone } doReturn expectedPhone
            on { website } doReturn expectedWebsite
        }

        val expectedAvatarUrl = "url"
        val params = mock<UserDetailsCreator.Params> {
            on { user } doReturn expectedUser
            on { avatarUrl } doReturn expectedAvatarUrl
        }

        // When
        val userDetails = classUnderTest.create(params)

        // Then

        with(userDetails) {
            kotlin.test.assertEquals(expectedId, id)
            kotlin.test.assertEquals(expectedName, name)
            kotlin.test.assertEquals(expectedUsername, username)
            kotlin.test.assertEquals(expectedEmail, email)
            kotlin.test.assertEquals(expectedPhone, phone)
            kotlin.test.assertEquals(expectedWebsite, website)
            kotlin.test.assertEquals(expectedAvatarUrl, avatarUrl)
        }
    }
}
package com.example.randomuser

import com.example.randomuser.data.datasource.local.PersonDao
import com.example.randomuser.data.datasource.local.PersonLocalSourceImpl
import com.example.randomuser.data.datasource.local.model.PersonEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonLocalSourceTest {
    lateinit var subject: PersonLocalSourceImpl

    @Mock
    lateinit var mockPersonDao: PersonDao

    @Before
    fun setup() {
        mockPersonDao = Mockito.mock()
        subject = PersonLocalSourceImpl(mockPersonDao)
    }

    @Test
    fun getSeedForTheFirstTime_expectedNull() {
        runBlocking {
            val actual = subject.getSeed()

            assertEquals(null, actual)
        }
    }

    @Test
    fun getExistingSeed_expectSomething() {
        runBlocking {
            val mockPersonEntity = PersonEntity(
                city = "",
                birthDate = "",
                contactPerson = "",
                contactPersonPhoneNum = "",
                country = "",
                email = "",
                firstName = "",
                lastName = "",
                mobileNumber = "",
                state = "",
                seed = "abc",
                page = 1,
            )

            Mockito.`when`(mockPersonDao.getOne()).thenReturn(mockPersonEntity)
            val actual = subject.getSeed()

            assertEquals(mockPersonEntity.seed, actual)
        }
    }
}

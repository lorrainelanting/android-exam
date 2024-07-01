package com.example.randomuser.data.repository.person

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.randomuser.data.datasource.local.PersonLocalSource
import com.example.randomuser.data.datasource.remote.PersonApiService
import com.example.randomuser.data.util.MAX_PAGE_SIZE
import com.example.randomuser.data.util.PAGE_SIZE
import com.example.randomuser.domain.model.Person
import com.example.randomuser.domain.repository.person.PersonRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepositoryImpl @Inject constructor(
    private val personApiService: PersonApiService,
    private val personLocalSource: PersonLocalSource,
) :
    PersonRepository {

    override suspend fun getPersons(forceUpdate: Boolean): Flow<PagingData<Person>> {
        var seed = personLocalSource.getSeed() ?: Date().toString()
        if (forceUpdate) {
            seed = Date().toString()
        }

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, maxSize = MAX_PAGE_SIZE),
            pagingSourceFactory = {
                PersonsPagingSource(
                    personApiService,
                    personLocalSource,
                    seed,
                )
            },
        ).flow
    }

    override suspend fun getPerson(email: String): Person? {
        try {
            val personEntity = personLocalSource.getPersonByEmail(email)
            return personEntity?.let {
                Person(
                    birthDate = personEntity.birthDate,
                    city = personEntity.city,
                    contactPerson = personEntity.contactPerson,
                    contactPersonPhoneNum = personEntity.contactPersonPhoneNum,
                    country = personEntity.country,
                    email = it.email,
                    firstName = personEntity.firstName,
                    lastName = personEntity.lastName,
                    mobileNumber = personEntity.mobileNumber,
                    state = personEntity.state,
                )
            }
        } catch (e: Exception) {
            Log.d("DEBUG_TEST", "PersonRepositoryImpl getPerson")
            throw e
        }
    }
}

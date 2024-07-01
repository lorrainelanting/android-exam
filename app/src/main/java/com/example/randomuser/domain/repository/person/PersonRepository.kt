package com.example.randomuser.domain.repository.person

import androidx.paging.PagingData
import com.example.randomuser.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    /** Get paginated list of [Person].***/
    suspend fun getPersons(forceUpdate: Boolean = false): Flow<PagingData<Person>>

    /** Get [Person] by email. ***/
    suspend fun getPerson(email: String): Person?
}

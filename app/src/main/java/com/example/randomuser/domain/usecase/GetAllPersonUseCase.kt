package com.example.randomuser.domain.usecase

import androidx.paging.PagingData
import com.example.randomuser.domain.model.Person
import com.example.randomuser.domain.repository.person.PersonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Usecase to get the Flow<PagingData<Person>> from [personRepository] to supply in [ViewModel]**/
class GetAllPersonUseCase @Inject constructor(
    private val personRepository: PersonRepository,
) {
    suspend operator fun invoke(forceUpdate: Boolean): Flow<PagingData<Person>> {
        return personRepository.getPersons(forceUpdate)
    }
}

package com.example.randomuser.domain.usecase

import com.example.randomuser.domain.model.Person
import com.example.randomuser.domain.repository.person.PersonRepository
import javax.inject.Inject

/** Usecase to get the FPerson? from [personRepository] to supply in [ViewModel]**/
class GetPersonUseCase @Inject constructor(
    private val personRepository: PersonRepository,
) {
    suspend operator fun invoke(email: String): Person? {
        return personRepository.getPerson(email)
    }
}

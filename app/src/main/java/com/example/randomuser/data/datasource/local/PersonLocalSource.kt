package com.example.randomuser.data.datasource.local

import com.example.randomuser.data.datasource.local.model.PersonEntity

interface PersonLocalSource {
    suspend fun appendPersons(persons: List<PersonEntity>)
    suspend fun getSeed(): String?
    suspend fun getPersons(page: Int, perPage: Int, seed: String): List<PersonEntity>
    suspend fun getPersonByEmail(email: String): PersonEntity?
}

package com.example.randomuser.data.datasource.local

import android.util.Log
import com.example.randomuser.data.datasource.local.model.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonLocalSourceImpl @Inject constructor(
    private val personDao: PersonDao,
) : PersonLocalSource {

    override suspend fun getSeed(): String? {
        return coroutineScope {
            withContext(Dispatchers.IO) {
                personDao.getOne()?.seed
            }
        }
    }

    override suspend fun getPersons(page: Int, perPage: Int, seed: String): List<PersonEntity> {
        try {
            if (getSeed() != seed) {
                return emptyList()
            }
            return coroutineScope {
                withContext(Dispatchers.IO) {
                    val result = personDao.getAll(page, seed)
                    Log.d(
                        "DEBUG_TEST",
                        "PersonLocalSourceImpl.getPersons($page, $perPage, $seed) size: ${result.size}",
                    )
                    result
                }
            }
        } catch (e: Exception) {
            Log.e("DEBUG_TEST", "PersonLocalSourceImpl.getPersons ERROR$e")
            return emptyList()
        }
    }

    override suspend fun getPersonByEmail(email: String): PersonEntity? {
        try {
            return getSeed()?.let { personDao.getOne(email, it) }
        } catch (e: Exception) {
            Log.e("DEBUG_TEST", "PersonLocalSourceImpl.getPersonByEmail ERROR$e")
            throw e
        }
    }

    override suspend fun appendPersons(persons: List<PersonEntity>) {
        return coroutineScope {
            withContext(Dispatchers.IO) {
                if (persons.isEmpty()) {
                    Log.e("DEBUG_TEST", "PersonLocalSourceImpl.appendPersons ERROR nothing to add")
                    return@withContext
                }
                if (getSeed() != persons.first().seed) {
                    Log.d("DEBUG_TEST", "PersonLocalSourceImpl.appendPersons clearing dirty cache")
                    personDao.clear()
                }
                persons.forEach {
                    val result = personDao.insert(it)

                    Log.d("DEBUG_TEST", "PersonLocalSourceImpl.appendPerson ${it.email}")
                }
            }
        }
    }
}

package com.example.randomuser.data.repository.person

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randomuser.data.datasource.local.PersonLocalSource
import com.example.randomuser.data.datasource.local.model.PersonEntity
import com.example.randomuser.data.datasource.remote.PersonApiService
import com.example.randomuser.data.util.PAGINATION_PER_PAGE
import com.example.randomuser.data.util.PAGINATION_TOTAL_PAGES
import com.example.randomuser.domain.model.Person

class PersonsPagingSource(
    private val personApiService: PersonApiService,
    private val personLocalSource: PersonLocalSource,
    private var seed: String,
) : PagingSource<Int, Person>() {

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val page = params.key ?: 1
            val perPage = PAGINATION_PER_PAGE

            Log.d(
                "DEBUG_TEST",
                "PersonsPagingSource.load seed: $seed, page: $page, perPage: $perPage, pages: $PAGINATION_TOTAL_PAGES",
            )

            var persons = personLocalSource.getPersons(page, perPage, seed).map {
                Person(
                    city = it.city,
                    birthDate = it.birthDate,
                    contactPerson = it.contactPerson,
                    contactPersonPhoneNum = it.contactPersonPhoneNum,
                    country = it.country,
                    email = it.email,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    mobileNumber = it.mobileNumber,
                    state = it.state,
                )
            }
            // try to get from local
            if (persons.isNotEmpty()) {
                // TODO: Unit test this path.
                Log.d("DEBUG_TEST", "PersonsPagingSource.load from LOCAL")
            }
            // try to get from remote
            else {
                // TODO: Unit test this path.
                Log.d("DEBUG_TEST", "PersonsPagingSource.load from REMOTE")
                val data = personApiService.getUsers(
                    page = page,
                    results = perPage,
                    seed = seed,
                )

                Log.d("DEBUG_TEST", "PersonsPagingSource.load count ${data.results.size}")

                seed = data.info.seed

                val result: List<Person> = data.results.map {
                    Person(
                        city = it.address.city,
                        birthDate = it.birthDate.date,
                        contactPerson = it.contactPerson,
                        contactPersonPhoneNum = it.contactPersonPhoneNum,
                        country = it.address.country,
                        email = it.email,
                        firstName = it.name.first,
                        lastName = it.name.last,
                        mobileNumber = it.mobileNumber,
                        state = it.address.state,
                    )
                }

                personLocalSource.appendPersons(
                    persons = result.map {
                        PersonEntity(
                            city = it.city,
                            birthDate = it.birthDate,
                            contactPerson = it.contactPerson,
                            contactPersonPhoneNum = it.contactPersonPhoneNum,
                            country = it.country,
                            email = it.email,
                            firstName = it.firstName,
                            lastName = it.lastName,
                            mobileNumber = it.mobileNumber,
                            state = it.state,
                            seed = seed,
                            page = page,
                        )
                    },
                )

                persons = personLocalSource.getPersons(page, perPage, seed).map {
                    Person(
                        city = it.city,
                        birthDate = it.birthDate,
                        contactPerson = it.contactPerson,
                        contactPersonPhoneNum = it.contactPersonPhoneNum,
                        country = it.country,
                        email = it.email,
                        firstName = it.firstName,
                        lastName = it.lastName,
                        mobileNumber = it.mobileNumber,
                        state = it.state,
                    )
                }
            }

            persons.forEach {
                Log.d("DEBUG_TEST", "PersonsPagingSource.loaded ${it.email}")
            }

            LoadResult.Page(
                data = persons,
                prevKey = if (page == 1) null else (page - 1),
                nextKey = if (page == PAGINATION_TOTAL_PAGES) null else (page + 1),
            )
        } catch (e: Exception) {
            Log.d("DEBUG_TEST", "PersonsPagingSource.load ERROR $e")
            LoadResult.Error(e)
        }
    }
}

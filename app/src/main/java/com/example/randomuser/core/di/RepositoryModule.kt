package com.example.randomuser.core.di

import com.example.randomuser.data.repository.person.PersonRepositoryImpl
import com.example.randomuser.domain.repository.person.PersonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun providesPersonRepository(repository: PersonRepositoryImpl): PersonRepository
}

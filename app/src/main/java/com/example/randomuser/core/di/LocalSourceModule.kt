package com.example.randomuser.core.di

import com.example.randomuser.data.datasource.local.PersonLocalSource
import com.example.randomuser.data.datasource.local.PersonLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalSourceModule {

    @Binds
    fun providesPersonLocalSource(personLocalSourceImpl: PersonLocalSourceImpl): PersonLocalSource
}

package com.example.randomuser.core.di

import com.example.randomuser.data.datasource.remote.PersonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesPersonApiService(): PersonApiService {
        return PersonApiService.getInstance()
    }
}

package com.example.randomuser.core.di

import com.example.randomuser.domain.repository.person.PersonRepository
import com.example.randomuser.domain.usecase.GetAllPersonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun providesGetaAllPersonUseCase(repository: PersonRepository): GetAllPersonUseCase {
        return GetAllPersonUseCase(repository)
    }
}

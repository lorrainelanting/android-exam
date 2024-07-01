package com.example.randomuser.core.di

import android.app.Person
import android.content.Context
import com.example.randomuser.data.datasource.local.AppRoomDatabase
import com.example.randomuser.data.datasource.local.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppRoomDatabase {
        return AppRoomDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providesPersonDao(db: AppRoomDatabase): PersonDao {
        return db.personDao()
    }
}

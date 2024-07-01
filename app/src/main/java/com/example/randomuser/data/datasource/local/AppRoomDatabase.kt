package com.example.randomuser.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomuser.data.datasource.local.model.PersonEntity
import com.example.randomuser.data.util.DATABASE_NAME

@Database(
    entities = [
        PersonEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(
            context: Context,
        ): AppRoomDatabase {
            return Room.databaseBuilder(
                context,
                AppRoomDatabase::class.java,
                DATABASE_NAME,
            ).build()
        }
    }
}

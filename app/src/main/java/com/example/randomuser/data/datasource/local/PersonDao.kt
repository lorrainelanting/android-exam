package com.example.randomuser.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomuser.data.datasource.local.model.PersonEntity

@Dao
interface PersonDao {
    @Query("SELECT * FROM persons WHERE page = :page AND seed = :seed")
    fun getAll(page: Int, seed: String): List<PersonEntity>

    @Query("SELECT * FROM persons LIMIT 1")
    fun getOne(): PersonEntity?

    @Query("SELECT * FROM persons WHERE seed = :seed AND email = :email LIMIT 1")
    fun getOne(email: String, seed: String): PersonEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(personEntity: PersonEntity)

    @Query("DELETE FROM persons WHERE 1")
    fun clear()
}

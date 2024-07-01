package com.example.randomuser.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("persons")
data class PersonEntity(
    val birthDate: String,
    val city: String,
    val contactPerson: String?,
    val contactPersonPhoneNum: String?,
    val country: String,
    @PrimaryKey val email: String,
    val firstName: String,
    val lastName: String,
    val mobileNumber: String,
    val state: String,
    val seed: String,
    val page: Int,
)

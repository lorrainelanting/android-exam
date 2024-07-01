package com.example.randomuser.domain.model

/** Model for ViewModel use . **/
data class Person(
    val birthDate: String,
    val city: String,
    val contactPerson: String?,
    val contactPersonPhoneNum: String?,
    val country: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val mobileNumber: String,
    val state: String,
)

package com.example.randomuser.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @field:SerializedName("location") val address: LocationResponse,
    @field:SerializedName("dob") val birthDate: DateOfBirthResponse,
    @field:SerializedName("contactPerson") val contactPerson: String?,
    @field:SerializedName("contactPersonPhoneNum") val contactPersonPhoneNum: String?,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("cell") val mobileNumber: String,
    @field:SerializedName("name") val name: NameResponse
)

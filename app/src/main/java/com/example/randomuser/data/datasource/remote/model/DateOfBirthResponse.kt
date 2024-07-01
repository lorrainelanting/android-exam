package com.example.randomuser.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class DateOfBirthResponse(
    @field:SerializedName("date") val date: String
)

package com.example.randomuser.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @field:SerializedName("city") val city: String,
    @field:SerializedName("state") val state: String,
    @field:SerializedName("country") val country: String
)

package com.example.randomuser.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @field:SerializedName("seed") val seed: String,
    @field:SerializedName("results") val results: Int,
    @field:SerializedName("page") val page: Int,
)

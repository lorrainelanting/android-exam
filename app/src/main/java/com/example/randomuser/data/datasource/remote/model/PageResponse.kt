package com.example.randomuser.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class PageResponse<T>(
    @field:SerializedName("results") val results: List<T>,
    @field:SerializedName("info") val info: InfoResponse,
)

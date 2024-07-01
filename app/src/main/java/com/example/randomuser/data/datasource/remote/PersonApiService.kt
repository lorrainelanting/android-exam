package com.example.randomuser.data.datasource.remote

import com.example.randomuser.data.datasource.remote.model.PageResponse
import com.example.randomuser.data.datasource.remote.model.PersonResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface PersonApiService {

    /** Get all users from remote. **/
    @GET("$BASE_URL?$SELECTED_PARAMS")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String?,
    ): PageResponse<PersonResponse>

    companion object {
        private const val BASE_URL = "https://randomuser.me/api/"
        private const val SELECTED_PARAMS = "inc=gender,name,dob,email,cell,location,id"

        fun getInstance(): PersonApiService {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit.create(PersonApiService::class.java)
        }
    }
}

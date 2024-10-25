package com.suitmedia.assignment.data.remote

import com.suitmedia.assignment.data.model.ApiResponse
import com.suitmedia.assignment.data.model.Profile
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MyAPI {
    @GET("api/users")
    suspend fun getProfile(@Header("Accept") accept: String = "application/json",
                           @Header("Authorization") authorization: String,
                           @Query("page") page: Int? = null,
                           @Query("per_page") perPage: Int? = null ): ApiResponse
}
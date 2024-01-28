package com.org.test.geminisample.remote.rest.api

import com.org.test.geminisample.remote.rest.model.ResultApi
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("forecast")
    suspend fun getSummary2(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("past_days") pastDays: Int,
        @Query("hourly") hourly: String
    ): ResultApi
}
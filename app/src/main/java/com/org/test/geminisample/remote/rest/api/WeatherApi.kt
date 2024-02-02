package com.org.test.geminisample.remote.rest.api

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    suspend fun fetchWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("past_days") pastDays: Int,
        @Query("hourly") hourly: String
    ): WeatherApi

}
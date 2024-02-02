package com.org.test.geminisample.data.domain.repository

import com.org.test.geminisample.remote.rest.api.WeatherApi

interface WeatherRepository {

    suspend fun fetchWeatherData(
        latitude: Double,
        longitude: Double,
        pastDays: Int,
        hourly: String
    ): WeatherApi

}
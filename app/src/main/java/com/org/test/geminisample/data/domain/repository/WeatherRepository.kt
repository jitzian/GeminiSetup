package com.org.test.geminisample.data.domain.repository

import com.org.test.geminisample.remote.rest.model.ResultApi

interface WeatherRepository {

    suspend fun fetchWeatherData(
        latitude: Double,
        longitude: Double,
        pastDays: Int,
        hourly: String,
        daily: String,
        temperatureUnit: String,
        timezone: String,
    ): ResultApi

}
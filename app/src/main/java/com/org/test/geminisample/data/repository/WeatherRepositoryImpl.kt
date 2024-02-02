package com.org.test.geminisample.data.repository

import com.org.test.geminisample.data.domain.repository.WeatherRepository
import com.org.test.geminisample.remote.rest.api.WeatherApi
import com.org.test.geminisample.remote.rest.model.ResultApi
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun fetchWeatherData(
        latitude: Double,
        longitude: Double,
        pastDays: Int,
        hourly: String
    ): ResultApi = weatherApi.fetchWeatherData( latitude, longitude, pastDays, hourly)
}
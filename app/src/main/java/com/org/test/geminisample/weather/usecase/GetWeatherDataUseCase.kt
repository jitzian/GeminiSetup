package com.org.test.geminisample.weather.usecase

import com.org.test.geminisample.data.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend fun fetchWeatherData(
        latitude: Double,
        longitude: Double,
        pastDays: Int,
        hourly: String
    ) = repository.fetchWeatherData(latitude, longitude, pastDays, hourly)

}
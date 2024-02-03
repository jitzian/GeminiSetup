package com.org.test.geminisample.remote.rest.api

import com.org.test.geminisample.remote.rest.model.ResultApi
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    /**
     * https://api.open-meteo.com/v1/forecast?
     * latitude=40.0552&
     * longitude=-83.1288&
     * hourly=temperature_2m,snow_depth&
     * daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration&
     * temperature_unit=fahrenheit
     * &timezone=auto*/
    @GET("forecast")
    suspend fun fetchWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("past_days") pastDays: Int,
        @Query("hourly") hourly: String,
        @Query("daily") daily: String,
        @Query("temperature_unit") temperatureUnit: String,
        @Query("timezone") timezone: String,
    ): ResultApi

}
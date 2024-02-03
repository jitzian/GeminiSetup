package com.org.test.geminisample.weather.usecase

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.org.test.geminisample.data.domain.repository.WeatherRepository
import com.org.test.geminisample.data.model.LocationData
import com.org.test.geminisample.utils.ktx.toStringList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val generativeModel: GenerativeModel,
) {
    private val TAG = "GetWeatherDataUseCase"
    private val scope = CoroutineScope(
        Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "CoroutineExceptionHandler: ${throwable.message}")
        }
    )

    suspend fun prepareData(
        latitude1: Double,
        longitude1: Double,
        latitude2: Double,
        longitude2: Double,
        pastDays: Int,
        hourly: String,
        daily: String,
        temperatureUnit: String,
        timezone: String
    ): List<LocationData> = scope.async {

        val location1 = async {
            repository.fetchWeatherData(
                latitude1,
                longitude1,
                pastDays,
                hourly,
                daily,
                temperatureUnit,
                timezone
            )
        }.await()

        val location2 = async {
            repository.fetchWeatherData(
                latitude2,
                longitude2,
                pastDays,
                hourly,
                daily,
                temperatureUnit,
                timezone
            )
        }.await()

        /*val timeLocation1 = location1.hourly.time.toStringList
        val windLocation1 = location1.hourly.wind_speed_10m.map { it.toString() }.toStringList

        val timeLocation2 = location2.hourly.time.toStringList
        val windLocation2 = location2.hourly.wind_speed_10m.map { it.toString() }.toStringList

        val prompt = """
            Given the following weather data:
            for location 1 the time/date is $timeLocation1 and the wind speed is $windLocation1
            and for location 2 the time/date is $timeLocation2 and the wind speed is $windLocation2
            generate a list separated by commas with the next 10 days and another list with the wind speed for each day that
            will be calculated based on an average of $windLocation1 and $windLocation2. This second list should be only for 10 days as well
        """.trimIndent()
        generativeModel.generateContent(prompt).text?.let {
            Log.e(TAG, "prepareData::generativeModel::it::$it")
        }*/

        listOf(
            LocationData(
                date = location1.hourly.time,
                windSpeed = location1.hourly.wind_speed_10m,
            ),
            LocationData(
                date = location2.hourly.time,
                windSpeed = location2.hourly.wind_speed_10m,
            )
        )
    }.await()

    suspend fun generateProjectionData(
        location1Data: LocationData,
        location2Data: LocationData
    ): LocationData {
        val timeLocation1 = location1Data.date.toStringList
        val windLocation1 = location1Data.windSpeed.map { it.toString() }.toStringList

        val timeLocation2 = location2Data.date.toStringList
        val windLocation2 = location2Data.windSpeed.map { it.toString() }.toStringList


        val prompt = """
            Given the following weather data:
            for location 1 the time/date is $timeLocation1 and the wind speed is $windLocation1
            and for location 2 the time/date is $timeLocation2 and the wind speed is $windLocation2
            generate a list separated by commas with the next 10 days and another list with the wind speed for each day that
            will be calculated based on an average of $windLocation1 and $windLocation2. This second list should be only for 10 days as well
            as the values for the next 10 days should be in the format of double values.
            
            An example of the expected output is: 
            - 2024-02-09: 10.1
            - 2024-02-10: 13.4
            
        """.trimIndent()
        generativeModel.generateContent(prompt).text?.let { response ->
            Log.e(TAG, "generateProjectionData::generativeModel::it::$response")
            //

            val dateWindSpeedPairs = response.lines()
            val dates = mutableListOf<String>()
            val windSpeeds = mutableListOf<Double>()

            for (pair in dateWindSpeedPairs) {
                val (date, windSpeed) = pair.split(":").map { it.trim() }
                dates.add(date.removePrefix("- "))
                windSpeeds.add(windSpeed.toDouble())
            }

            return LocationData(
                date = dates,
                windSpeed = windSpeeds
            )
        }
        return LocationData(emptyList(), emptyList())
    }

}


package com.org.test.geminisample.feature.weather.usecase

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.org.test.geminisample.data.domain.repository.WeatherRepository
import com.org.test.geminisample.data.model.LocationsData
import com.org.test.geminisample.utils.ktx.toStringList
import com.org.test.geminisample.feature.weather.mapper.WindLocationToListPointMapper
import com.org.test.geminisample.feature.weather.ui.MenuSelection
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import javax.inject.Inject
import kotlin.random.Random

class GetWeatherDataUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val generativeModel: GenerativeModel,
    private val windLocationToListPointMapper: WindLocationToListPointMapper,
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
        timezone: String,
        selection: MenuSelection? = MenuSelection.SELECTION_1,
    ): LocationsData = scope.async {

        try {
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

            val dateLocation1 = location1.hourly.time.map { it }.toStringList
            val windSpeedLocation1 =
                location1.hourly.wind_speed_10m.map { it.toString() }.toStringList

            val dateLocation2 = location2.hourly.time.map { it }.toStringList
            val windSpeedLocation2 =
                location2.hourly.wind_speed_10m.map { it.toString() }.toStringList

            val prompt = generativePrompt(
                dateLocation1,
                windSpeedLocation1,
                dateLocation2,
                windSpeedLocation2,
                selection?.param,
            )


            generativeModel.generateContent(prompt).text?.let { response ->
                Log.e(TAG, "prepareData_:generativeModel::it:: >>>>>>>> \uD83E\uDD16 $response")

                return@async LocationsData(
                    pointsDataLocation1 = windLocationToListPointMapper.invoke(
                        response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                    ),
                    pointsDataLocation2 = windLocationToListPointMapper.invoke(
                        //response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                        response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                            .map { (it.toFloat() + Random.nextInt(1, 11)).toString() }
                    ),
                    pointsDataProjection = windLocationToListPointMapper.invoke(
                        //response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                        response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                            .map { (it.toFloat() + Random.nextInt(1, 3)).toString() }
                    ),
                    dates = location1.hourly.time
                )
            }

            return@async LocationsData()
            /*return@async LocationsData(
                pointsDataLocation1 = windLocationToListPointMapper.invoke(
                    response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                ),
                pointsDataLocation2 = windLocationToListPointMapper.invoke(
                    //response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                    response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                        .map { (it.toFloat() + Random.nextInt(1, 11)).toString() }
                ),
                pointsDataProjection = windLocationToListPointMapper.invoke(
                    //response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                    response.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                        .map { (it.toFloat() + Random.nextInt(1, 3)).toString() }
                ),
                dates = location1.hourly.time,
            )*/
        } catch (e: Exception) {
            Log.e(TAG, "prepareData::exception::${e.message}")
            return@async LocationsData()
        }
    }.await()

}

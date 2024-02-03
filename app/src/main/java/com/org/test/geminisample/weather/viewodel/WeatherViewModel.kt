package com.org.test.geminisample.weather.viewodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.test.geminisample.constants.GlobalConstants
import com.org.test.geminisample.di.component.DaggerGetWeatherDataUseCaseComponent
import com.org.test.geminisample.di.module.GenerativeModelModule
import com.org.test.geminisample.di.module.GetWeatherDataUseCaseModule
import com.org.test.geminisample.di.module.RemoteModule
import com.org.test.geminisample.di.module.WeatherRepositoryModule
import com.org.test.geminisample.weather.state.UIState
import com.org.test.geminisample.weather.usecase.GetWeatherDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class WeatherViewModel : ViewModel() {

    private val TAG = WeatherViewModel::class.java.simpleName

    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Initial)
    val state = _state.asStateFlow()

    @Inject
    lateinit var getWeatherDataUseCase: GetWeatherDataUseCase

    private val injector =
        DaggerGetWeatherDataUseCaseComponent
            .builder()
            .getWeatherDataUseCaseModule(GetWeatherDataUseCaseModule())
            .weatherRepositoryModule(WeatherRepositoryModule())
            .generativeModelModule(GenerativeModelModule())
            .remoteModule(RemoteModule(GlobalConstants.BASE_URL))
            .build()

    init {
        injector.inject(this)
    }

    fun fetchWeatherData() = viewModelScope.launch(Dispatchers.IO) {
        _state.update { UIState.Loading() }
        delay(2.seconds)
        val data = getWeatherDataUseCase.prepareData(
            latitude1 = 40.0552,
            longitude1 = -83.1288,
            latitude2 = 42.936450,
            longitude2 = -78.831590,
            pastDays = 10,
            hourly = "temperature_2m,relative_humidity_2m,wind_speed_10m,snow_depth",
            daily = "temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration",
            temperatureUnit = "fahrenheit",
            timezone = "auto"
        )

        when {
            data.isNotEmpty() -> {
                _state.update { UIState.Success(location1Data = data[0], location2Data = data[1]) }
                _state.update { UIState.Loading(message = "Fetching projection data") }
                _state.update {
                    //copy projection data
                    UIState.Success(
                        location1Data = data[0],
                        location2Data = data[1],
                        projectionData = getWeatherDataUseCase.generateProjectionData(
                            data[0],
                            data[1]
                        )
                    )
                }
                Log.e(TAG, "fetchWeatherData::${(_state.value as UIState.Success).location1Data.date}")
                Log.e(TAG, "fetchWeatherData::${(_state.value as UIState.Success).location1Data.windSpeed}")
                Log.e(TAG, "---------------------fetchWeatherData:---------------------")
                Log.e(TAG, "fetchWeatherData::${(_state.value as UIState.Success).location2Data.date}")
                Log.e(TAG, "fetchWeatherData::${(_state.value as UIState.Success).location2Data.windSpeed}")
                Log.e(TAG, "---------------------fetchWeatherData:---------------------")
                Log.e(TAG, "fetchWeatherData::${(_state.value as UIState.Success).projectionData?.date}")
                Log.e(TAG, "fetchWeatherData::${(_state.value as UIState.Success).projectionData?.windSpeed}")
            }

            else -> {
                _state.update { UIState.Error("No data found") }
            }
        }
    }

}
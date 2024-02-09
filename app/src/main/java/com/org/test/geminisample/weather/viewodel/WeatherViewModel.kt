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
import com.org.test.geminisample.weather.ui.MenuSelection
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

    fun fetchWeatherData(selection: MenuSelection? = MenuSelection.SELECTION_1) =
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { UIState.Loading() }
            delay(2.seconds)
            val data = getWeatherDataUseCase.prepareData(
                latitude1 = 40.0552,
                longitude1 = -83.1288,
                latitude2 = 42.936450,
                longitude2 = -78.831590,
                pastDays = 5,
                //TODO: Move these to a constants file
                hourly = "temperature_2m,relative_humidity_2m,wind_speed_10m,snow_depth",
                daily = "temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration",
                temperatureUnit = "fahrenheit",
                timezone = "auto",
                selection = selection,
            )

            Log.e(TAG, "fetchWeatherData::data:: --->> $data")

            when {
                data.pointsDataLocation1.isNotEmpty() && data.pointsDataLocation2.isNotEmpty()
                        && data.pointsDataProjection.isNotEmpty() -> {
                    _state.update {
                        UIState.Success(
                            pointsDataLocation1 = data.pointsDataLocation1,
                            pointsDataLocation2 = data.pointsDataLocation2,
                            pointsDataProjection = data.pointsDataProjection,
                            dates = data.dates
                        )
                    }
                }

                else -> {
                    _state.update { UIState.Error("No data found") }
                }
            }
        }

}
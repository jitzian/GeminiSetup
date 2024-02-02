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
import com.org.test.geminisample.weather.usecase.GetWeatherDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel : ViewModel() {

    private val TAG = WeatherViewModel::class.java.simpleName

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

    fun fetchWeatherData() = viewModelScope.launch {
        val data = getWeatherDataUseCase.fetchWeatherData(
            latitude = 52.52,
            longitude = 13.41,
            pastDays = 10,
            hourly = "temperature_2m,relative_humidity_2m,wind_speed_10m"

        )

        Log.e(TAG, "fetchWeatherData::data::$data")

    }


}
package com.org.test.geminisample.di.module

import com.org.test.geminisample.data.domain.repository.WeatherRepository
import com.org.test.geminisample.weather.usecase.GetWeatherDataUseCase
import dagger.Module
import dagger.Provides

@Module
class GetWeatherDataUseCaseModule {

    @Provides
    fun providesGetWeatherDataUseCase(weatherRepository: WeatherRepository): GetWeatherDataUseCase {
        return GetWeatherDataUseCase(weatherRepository)
    }
}
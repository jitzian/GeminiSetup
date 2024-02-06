package com.org.test.geminisample.di.module

import com.google.ai.client.generativeai.GenerativeModel
import com.org.test.geminisample.data.domain.repository.WeatherRepository
import com.org.test.geminisample.weather.mapper.WindLocationToListPointMapper
import com.org.test.geminisample.weather.usecase.GetWeatherDataUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class GetWeatherDataUseCaseModule {
    @Provides
    @Reusable
    fun providesGetWeatherDataUseCase(
        weatherRepository: WeatherRepository,
        generativeModel: GenerativeModel,
        windLocationToListPointMapper: WindLocationToListPointMapper,
    ): GetWeatherDataUseCase =
        GetWeatherDataUseCase(
            weatherRepository,
            generativeModel,
            windLocationToListPointMapper
        )

}
package com.org.test.geminisample.di.module

import com.org.test.geminisample.data.domain.repository.WeatherRepository
import com.org.test.geminisample.data.repository.WeatherRepositoryImpl
import com.org.test.geminisample.remote.rest.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class WeatherRepositoryModule {
    @Provides
    @Reusable
    fun providesWeatherRepository(weatherApi: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(weatherApi)

}
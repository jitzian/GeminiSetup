package com.org.test.geminisample.di.module

import com.org.test.geminisample.data.domain.repository.WeatherRepository
import com.org.test.geminisample.data.repository.WeatherRepositoryImpl
import com.org.test.geminisample.remote.rest.api.WeatherApi
import dagger.Binds
import dagger.Module
import dagger.Provides

//@Module
///*abstract*/
//class WeatherRepositoryModule {
//    /*@Binds
//    abstract fun weatherRepository(
//        weatherRepositoryImpl: WeatherRepositoryImpl
//    ): WeatherRepository*/
//
//    @Provides
//    fun providesWeatherRepository(weatherApi: WeatherApi) = WeatherRepositoryImpl(weatherApi)
//}

//@Module
//class WeatherRepositoryModule {
//    @Provides
//    fun providesWeatherRepository(weatherApi: WeatherApi) = WeatherRepositoryImpl(weatherApi)
//}

@Module
class WeatherRepositoryModule {

    @Provides
    fun providesWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }
}
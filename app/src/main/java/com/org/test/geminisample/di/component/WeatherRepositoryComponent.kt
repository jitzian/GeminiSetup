package com.org.test.geminisample.di.component

import com.org.test.geminisample.di.module.NetworkModule
import com.org.test.geminisample.di.module.WeatherRepositoryModule
import com.org.test.geminisample.feature.weather.usecase.GetWeatherDataUseCase
import dagger.Component

@Component(
    modules = [
        WeatherRepositoryModule::class,
        NetworkModule::class,
    ]
)
interface WeatherRepositoryComponent {

    fun inject(weatherDataUseCase: GetWeatherDataUseCase)

    @Component.Builder
    interface Builder {
        fun weatherRepositoryModule(weatherRepositoryModule: WeatherRepositoryModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): WeatherRepositoryComponent
    }

}
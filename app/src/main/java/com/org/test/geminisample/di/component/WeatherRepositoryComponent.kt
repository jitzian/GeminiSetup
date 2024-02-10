package com.org.test.geminisample.di.component

import com.org.test.geminisample.di.module.RemoteModule
import com.org.test.geminisample.di.module.WeatherRepositoryModule
import com.org.test.geminisample.feature.weather.usecase.GetWeatherDataUseCase
import dagger.Component

@Component(
    modules = [
        WeatherRepositoryModule::class,
        RemoteModule::class,
    ]
)
interface WeatherRepositoryComponent {

    fun inject(weatherDataUseCase: GetWeatherDataUseCase)

    @Component.Builder
    interface Builder {
        fun weatherRepositoryModule(weatherRepositoryModule: WeatherRepositoryModule): Builder
        fun remoteModule(remoteModule: RemoteModule): Builder
        fun build(): WeatherRepositoryComponent
    }

}
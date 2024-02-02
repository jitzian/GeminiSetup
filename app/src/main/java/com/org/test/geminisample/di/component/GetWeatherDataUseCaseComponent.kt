package com.org.test.geminisample.di.component

import com.org.test.geminisample.di.module.GenerativeModelModule
import com.org.test.geminisample.di.module.GetWeatherDataUseCaseModule
import com.org.test.geminisample.di.module.RemoteModule
import com.org.test.geminisample.di.module.WeatherRepositoryModule
import com.org.test.geminisample.weather.viewodel.WeatherViewModel
import dagger.Component

@Component(
    modules = [
        GetWeatherDataUseCaseModule::class,
        WeatherRepositoryModule::class,
        GenerativeModelModule::class,
        RemoteModule::class,
    ]
)
interface GetWeatherDataUseCaseComponent {

    fun inject(weatherViewModel: WeatherViewModel)

    @Component.Builder
    interface Builder {
        fun getWeatherDataUseCaseModule(getWeatherDataUseCaseModule: GetWeatherDataUseCaseModule): Builder
        fun weatherRepositoryModule(weatherRepositoryModule: WeatherRepositoryModule): Builder
        fun generativeModelModule(generativeModelModule: GenerativeModelModule): Builder
        fun remoteModule(remoteModule: RemoteModule): Builder
        fun build(): GetWeatherDataUseCaseComponent
    }
}
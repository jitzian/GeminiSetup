package com.org.test.geminisample.di.component

import com.org.test.geminisample.data.domain.repository.WeatherRepository
import com.org.test.geminisample.di.module.RemoteModule
import dagger.Component

@Component(
    modules = [
        RemoteModule::class
    ]
)
interface RemoteComponent {

    fun inject(weatherRepository: WeatherRepository)

    @Component.Builder
    interface Builder {
        fun remoteModule(remoteModule: RemoteModule): Builder
        fun build(): RemoteComponent
    }

}
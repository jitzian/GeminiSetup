package com.org.test.geminisample.di.component

import com.org.test.geminisample.di.module.GenerativeModelModule
import com.org.test.geminisample.feature.weather.usecase.GetWeatherDataUseCase
import dagger.Component

@Component(
    modules = [
        GenerativeModelModule::class
    ]
)
interface GenerativeModelComponent {

    fun inject(getWeatherDataUseCase: GetWeatherDataUseCase)

    @Component.Builder
    interface Builder {
        fun generativeModelModule(generativeModelModule: GenerativeModelModule): Builder
        fun build(): GenerativeModelComponent
    }
}
package com.org.test.geminisample.di.component

import com.org.test.geminisample.di.module.NetworkModule
import com.org.test.geminisample.summary.viewmodel.SummarizeViewModel
import dagger.Component

@Component(
    modules = [
        NetworkModule::class
    ]
)
interface NetworkComponent {

    fun inject(summarizeViewModel: SummarizeViewModel)

    @Component.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): NetworkComponent

    }

}
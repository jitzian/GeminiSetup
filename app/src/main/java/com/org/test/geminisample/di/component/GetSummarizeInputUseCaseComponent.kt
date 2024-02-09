package com.org.test.geminisample.di.component

import com.org.test.geminisample.di.module.GenerativeModelModule
import com.org.test.geminisample.di.module.GetSummarizeInputUseCaseModule
import com.org.test.geminisample.summary.viewmodel.SummaryViewModel
import dagger.Component

@Component(
    modules = [
        GetSummarizeInputUseCaseModule::class,
        GenerativeModelModule::class,
    ]
)
interface GetSummarizeInputUseCaseComponent {

    fun inject(summaryViewModel: SummaryViewModel)

    @Component.Builder
    interface Builder {
        fun getSummarizeInputUseCaseModule(getSummarizeInputUseCaseModule: GetSummarizeInputUseCaseModule): Builder
        fun generativeModelModule(generativeModelModule: GenerativeModelModule): Builder
        fun build(): GetSummarizeInputUseCaseComponent
    }

}
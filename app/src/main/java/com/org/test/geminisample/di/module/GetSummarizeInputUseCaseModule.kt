package com.org.test.geminisample.di.module

import com.google.ai.client.generativeai.GenerativeModel
import com.org.test.geminisample.weather.usecase.GetSummarizeInputUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class GetSummarizeInputUseCaseModule {

    @Provides
    @Reusable
    fun providesSummarizeInputUseCase(generativeModel: GenerativeModel) =
        GetSummarizeInputUseCase(generativeModel)

}
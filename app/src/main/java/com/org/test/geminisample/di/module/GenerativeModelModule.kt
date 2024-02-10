package com.org.test.geminisample.di.module

import com.google.ai.client.generativeai.GenerativeModel
import com.org.test.geminisample.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class GenerativeModelModule {

    @Provides
    @Reusable
    fun providesGenerativeModule() = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.apiKey
    )

}
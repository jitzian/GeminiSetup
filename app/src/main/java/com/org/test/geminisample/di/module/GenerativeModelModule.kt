package com.org.test.geminisample.di.module

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
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
        apiKey = BuildConfig.apiKey,
        safetySettings = listOf(
            SafetySetting(
                HarmCategory.HARASSMENT,
                BlockThreshold.ONLY_HIGH
            ),
            SafetySetting(
                HarmCategory.HATE_SPEECH,
                BlockThreshold.LOW_AND_ABOVE
            ),
            SafetySetting(
                HarmCategory.DANGEROUS_CONTENT,
                BlockThreshold.LOW_AND_ABOVE
            ),
            SafetySetting(
                HarmCategory.SEXUALLY_EXPLICIT,
                BlockThreshold.LOW_AND_ABOVE
            ),

        )
    )

}
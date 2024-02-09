package com.org.test.geminisample.feature.weather.usecase

import com.google.ai.client.generativeai.GenerativeModel
import com.org.test.geminisample.feature.summary.state.UIState
import javax.inject.Inject

class GetSummarizeInputUseCase @Inject constructor(
    private val generativeModel: GenerativeModel,
) {

    suspend fun summarizeInput(input: String) = try {
        UIState.Success(
            generativeModel.generateContent("Summarize the following text for me: $input").text
                ?: ""
        )
    } catch (e: Exception) {
        UIState.Error(e.message ?: "")
    }

}
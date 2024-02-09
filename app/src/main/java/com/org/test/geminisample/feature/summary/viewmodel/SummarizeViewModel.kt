package com.org.test.geminisample.feature.summary.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.org.test.geminisample.SummarizeUiState
import com.org.test.geminisample.constants.GlobalConstants.BASE_URL
import com.org.test.geminisample.di.component.DaggerNetworkComponent
import com.org.test.geminisample.di.module.GenerativeModelModule
import com.org.test.geminisample.di.module.NetworkModule
import com.org.test.geminisample.remote.rest.api.RestApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class SummarizeViewModel : ViewModel() {
    private val TAG = SummarizeViewModel::class.java.simpleName

    private val _uiState: MutableStateFlow<SummarizeUiState> =
        MutableStateFlow(SummarizeUiState.Initial)
    val uiState: StateFlow<SummarizeUiState> =
        _uiState.asStateFlow()

    private val injector = DaggerNetworkComponent.builder()
        .networkModule(NetworkModule(BASE_URL))
        .generativeModelModule(GenerativeModelModule())
        .build()

    @Inject
    lateinit var restApi: RestApi

    @Inject
    lateinit var generativeModel: GenerativeModel

    init {
        injector.inject(this)
        fetchData()
    }

    fun summarize(inputText: String) {
        _uiState.value = SummarizeUiState.Loading

        val prompt = "Summarize the following text for me: $inputText"

        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(prompt)
                response.text?.let { outputContent ->
                    _uiState.value = SummarizeUiState.Success(outputContent)
                }
            } catch (e: Exception) {
                _uiState.value = SummarizeUiState.Error(e.localizedMessage ?: "")
            }
        }
    }

    private fun fetchData() = viewModelScope.launch {
        delay(5.seconds)
        val data = restApi.getSummary2(
            latitude = 52.52,
            longitude = 13.41,
            pastDays = 10,
            hourly = "temperature_2m,relative_humidity_2m,wind_speed_10m"
        )
        Log.e(TAG, "fetchData::data::$data")
    }
}
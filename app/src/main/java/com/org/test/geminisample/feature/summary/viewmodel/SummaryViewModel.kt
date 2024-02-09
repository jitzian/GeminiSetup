package com.org.test.geminisample.feature.summary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.test.geminisample.di.component.DaggerGetSummarizeInputUseCaseComponent
import com.org.test.geminisample.di.module.GenerativeModelModule
import com.org.test.geminisample.di.module.GetSummarizeInputUseCaseModule
import com.org.test.geminisample.feature.summary.state.UIState
import com.org.test.geminisample.feature.weather.usecase.GetSummarizeInputUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SummaryViewModel : ViewModel() {

    @Inject
    lateinit var getSummarizeInputUseCase: GetSummarizeInputUseCase

    private val injector = DaggerGetSummarizeInputUseCaseComponent.builder()
        .generativeModelModule(GenerativeModelModule())
        .getSummarizeInputUseCaseModule(GetSummarizeInputUseCaseModule())
        .build()

    init {
        injector.inject(this)
    }


    private val _state = MutableStateFlow<UIState>(UIState.Initial)
    val state = _state.asStateFlow()


    fun summarizeInput(input: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.update { UIState.Loading }
        _state.update { getSummarizeInputUseCase.summarizeInput(input) }
    }


}
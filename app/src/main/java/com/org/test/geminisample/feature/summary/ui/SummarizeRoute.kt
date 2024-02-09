package com.org.test.geminisample.feature.summary.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.test.geminisample.feature.summary.viewmodel.SummarizeViewModel

@Composable
internal fun SummarizeRoute(
    summarizeViewModel: SummarizeViewModel = viewModel()
) {
    val summarizeUiState by summarizeViewModel.uiState.collectAsState()

    SummarizeScreen(
        uiState = summarizeUiState,
        onSummarizeClicked = { inputText ->
            summarizeViewModel.summarize(inputText)
        }
    )
}

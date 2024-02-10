package com.org.test.geminisample.feature.summary.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.test.geminisample.R
import com.org.test.geminisample.feature.summary.state.UIState
import com.org.test.geminisample.feature.summary.viewmodel.SummaryViewModel
import com.org.test.geminisample.ui.theme.GeminiSampleTheme
import com.org.test.geminisample.feature.weather.ui.ErrorScreen

@Composable
fun SummaryScreenState(viewModel: SummaryViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    when (state) {
        is UIState.Initial -> SummaryScreen(
            onSummarize = viewModel::summarizeInput,
            data = ""
        )

        is UIState.Success -> SummaryScreen(
            onSummarize = viewModel::summarizeInput,
            data = (state as UIState.Success).message
        )

        is UIState.Loading -> LoadingScreen()
        is UIState.Error -> ErrorScreen(message = (state as UIState.Error).errorMessage)
    }
}

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    onSummarize: (String) -> Unit,
    data: String = "",
) {
    var prompt by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(all = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            TextField(
                value = prompt,
                label = {
                    Text(
                        text = stringResource(R.string.summarize_label),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.summarize_hint),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                onValueChange = { prompt = it },
                modifier = Modifier
                    .weight(8f),
                textStyle = MaterialTheme.typography.bodyMedium,
            )
            TextButton(
                onClick = { if (prompt.isNotBlank()) onSummarize.invoke(prompt) },

                modifier = Modifier
                    .weight(2f)
                    .padding(all = 4.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(stringResource(R.string.action_go))
            }
        }
        if (data.isNotEmpty()) {
            Text(
                text = data,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun SummaryScreenPreview() = GeminiSampleTheme { SummaryScreenState() }

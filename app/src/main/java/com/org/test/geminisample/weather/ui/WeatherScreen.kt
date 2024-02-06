package com.org.test.geminisample.weather.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.test.geminisample.summary.ui.LoadingScreen
import com.org.test.geminisample.ui.theme.GeminiSampleTheme
import com.org.test.geminisample.weather.state.UIState
import com.org.test.geminisample.weather.viewodel.WeatherViewModel

@Composable
fun WeatherScreenState(
    weatherViewModel: WeatherViewModel = viewModel(),
) {
    val state by weatherViewModel.state.collectAsState()

    when (state) {
        is UIState.Initial -> weatherViewModel.fetchWeatherData()

        is UIState.Error -> {
            ErrorScreen(message = (state as UIState.Error).errorMessage)
        }

        is UIState.Loading -> LoadingScreen()
        is UIState.Success -> {
            Log.e("WeatherScreenState", "WeatherScreenState::GOTO SUCCESS!!")
            WeatherScreen(data = (state as UIState.Success))
        }
    }

}


@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    data: UIState.Success,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            content = {
                item {
                    WeatherCharts(
                        state = data,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
    }

}

@Preview
@Composable
private fun PreviewWeatherScreenState() {
    GeminiSampleTheme {
        WeatherScreenState()
    }
}
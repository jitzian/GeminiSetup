package com.org.test.geminisample.weather.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.test.geminisample.summary.ui.LoadingScreen
import com.org.test.geminisample.ui.theme.GeminiSampleTheme
import com.org.test.geminisample.weather.state.UIState
import com.org.test.geminisample.weather.ui.MenuSelection.*
import com.org.test.geminisample.weather.ui.common.CustomFloatingButton
import com.org.test.geminisample.weather.viewodel.WeatherViewModel

@Composable
fun WeatherScreenState(
    weatherViewModel: WeatherViewModel = viewModel(),
) {
    val state by weatherViewModel.state.collectAsState()

    when (state) {
        is UIState.Initial -> weatherViewModel.fetchWeatherData()
        is UIState.Error -> ErrorScreen(message = (state as UIState.Error).errorMessage)
        is UIState.Loading -> LoadingScreen()
        //is UIState.Success -> WeatherScreen(data = (state as UIState.Success))
        is UIState.Success -> WeatherScreen(
            data = (state as UIState.Success),
            fetchData = weatherViewModel::fetchWeatherData
        )
        //is UIState.Success -> ErrorScreen(message = "Something went wrong!", errorCode = "404")
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    data: UIState.Success,
    fetchData: ((MenuSelection?) -> Unit)? = null,
) {
    val menuExpanded = remember { mutableStateOf(false) }

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Weather") }) },
        content = { paddingValues ->
            LazyColumn(modifier = modifier
                .fillMaxSize()
                .padding(paddingValues), content = {
                item {
                    WeatherCharts(
                        state = data, modifier = Modifier.fillMaxWidth()
                    )
                }
            })
        },
        floatingActionButton = {
            Column {
                AnimatedVisibility(visible = menuExpanded.value) {
                    Column {
                        CustomFloatingButton(
                            content = "1",
                            modifier = Modifier.padding(bottom = 16.dp),
                            action = { fetchData?.invoke(SELECTION_1) }
                        )
                        CustomFloatingButton(
                            content = "2",
                            modifier = Modifier.padding(bottom = 16.dp),
                            action = { fetchData?.invoke(SELECTION_2) }
                        )
                        CustomFloatingButton(
                            content = "3",
                            modifier = Modifier.padding(bottom = 16.dp),
                            action = { fetchData?.invoke(SELECTION_3) }
                        )
                    }
                }
                FloatingActionButton(onClick = { menuExpanded.value = !menuExpanded.value }) {
                    Icon(
                        imageVector = if (menuExpanded.value) Icons.Default.KeyboardArrowDown
                        else Icons.Default.KeyboardArrowUp, contentDescription = null
                    )
                }
            }
        })

}

@Preview
@Composable
private fun PreviewWeatherScreenState() {
    GeminiSampleTheme {
        WeatherScreenState()
    }
}
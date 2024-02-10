@file:Suppress("DEPRECATION")

package com.org.test.geminisample.feature.weather.ui

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.org.test.geminisample.feature.summary.ui.LoadingScreen
import com.org.test.geminisample.feature.weather.state.UIState
import com.org.test.geminisample.feature.weather.ui.MenuSelection.SELECTION_1
import com.org.test.geminisample.feature.weather.ui.common.FloatingMenu
import com.org.test.geminisample.feature.weather.viewodel.WeatherViewModel
import com.org.test.geminisample.ui.theme.GeminiSampleTheme
import kotlinx.coroutines.launch

@Composable
fun WeatherScreenState(
    weatherViewModel: WeatherViewModel = viewModel(),
) {
    val state by weatherViewModel.state.collectAsState()

    when (state) {
        is UIState.Initial -> weatherViewModel.fetchWeatherData()
        is UIState.Error -> ErrorScreen(
            message = (state as UIState.Error).errorMessage,
            fetchData = weatherViewModel::fetchWeatherData,
        )

        is UIState.Loading -> LoadingScreen()
        is UIState.Success -> WeatherScreen(
            data = (state as UIState.Success),
            fetchData = weatherViewModel::fetchWeatherData,
        )
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

    Scaffold(content = { paddingValues ->

        val coroutineScope = rememberCoroutineScope()
        val isRefreshing = remember { mutableStateOf(false) }
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing.value)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                coroutineScope.launch {
                    isRefreshing.value = true
                    fetchData?.invoke(SELECTION_1)
                    isRefreshing.value = false
                }
            }
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                content = {
                    item {
                        WeatherCharts(
                            state = data, modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            )
        }
    },
        floatingActionButton = {
            Column {
                FloatingMenu(
                    modifier = Modifier.padding(bottom = 16.dp),
                    fetchData = { fetchData?.invoke(SELECTION_1) },
                    menuExpanded = menuExpanded.value,
                )
                FloatingActionButton(onClick = { menuExpanded.value = !menuExpanded.value }) {
                    Icon(
                        imageVector = if (menuExpanded.value) Icons.Default.KeyboardArrowDown
                        else Icons.Default.KeyboardArrowUp, contentDescription = null
                    )
                }
            }
        }
    )

}

@Preview
@Composable
private fun PreviewWeatherScreenState() = GeminiSampleTheme { WeatherScreenState() }

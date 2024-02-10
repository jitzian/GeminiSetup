@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.org.test.geminisample.feature.entry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.org.test.geminisample.feature.entry.Screens.SummaryScreen
import com.org.test.geminisample.feature.entry.Screens.WeatherScreen
import com.org.test.geminisample.feature.summary.ui.SummaryScreenState
import com.org.test.geminisample.feature.weather.ui.WeatherScreenState

enum class Screens(val title: String) {
    SummaryScreen("Summary"),
    WeatherScreen("Weather")
}

@Composable
fun MainBuilderUI(modifier: Modifier = Modifier) {
    // Define a state to hold the current selection
    var currentScreen by remember { mutableStateOf(SummaryScreen.title) }

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Gemini") }) },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                RadioGroup(
                    options = listOf(SummaryScreen.title, WeatherScreen.title),
                    selectedOption = currentScreen,
                    onSelectedChange = { currentScreen = it }
                )

                when (currentScreen) {
                    SummaryScreen.title -> SummaryScreenState()
                    WeatherScreen.title -> WeatherScreenState()
                }
            }
        }
    )
}
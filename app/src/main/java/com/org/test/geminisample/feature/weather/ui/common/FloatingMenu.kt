package com.org.test.geminisample.feature.weather.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.org.test.geminisample.ui.theme.GeminiSampleTheme

@Composable
fun FloatingMenu(
    modifier: Modifier = Modifier,
    fetchData: () -> Unit,
    menuExpanded: Boolean,
) {
    AnimatedVisibility(visible = menuExpanded, modifier = modifier) {
        Column {
            CustomFloatingButton(
                content = "1",
                modifier = Modifier.padding(bottom = 16.dp),
                action = fetchData
            )
            CustomFloatingButton(
                content = "2",
                modifier = Modifier.padding(bottom = 16.dp),
                action = fetchData
            )
            CustomFloatingButton(
                content = "3",
                modifier = Modifier.padding(bottom = 16.dp),
                action = fetchData
            )
        }
    }
}

@Preview
@Composable
private fun PrevFloatingMenu() = GeminiSampleTheme {
    FloatingMenu(
        fetchData = { },
        menuExpanded = true,
    )
}
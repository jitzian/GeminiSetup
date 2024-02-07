package com.org.test.geminisample.weather.ui.common

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomFloatingButton(
    modifier: Modifier = Modifier,
    content: String,
    action: (() -> Unit)? = null,
) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = { action?.invoke() },
        modifier = modifier,
        containerColor = Color.DarkGray,
        contentColor = Color.White,
    ) { Text(content) }
}

@Preview
@Composable
private fun PrevCustomFloatingButton() {
    CustomFloatingButton(
        content = "1",
        action = { }
    )
}
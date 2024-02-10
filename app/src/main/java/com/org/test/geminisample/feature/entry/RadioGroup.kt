package com.org.test.geminisample.feature.entry

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: String,
    onSelectedChange: (String) -> Unit
) {
    Row {
        options.forEach { text ->
            Row(
                Modifier
                    .padding(8.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onSelectedChange(text) }
                    ),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onSelectedChange(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevRadioGroup() {
    RadioGroup(
        options = listOf("SummaryScreen", "WeatherScreen"),
        selectedOption = "SummaryScreen",
        onSelectedChange = { }
    )
}
@file:Suppress("DEPRECATION")

package com.org.test.geminisample.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.org.test.geminisample.R
import com.org.test.geminisample.ui.theme.GeminiSampleTheme
import kotlinx.coroutines.launch

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorCode: String? = null,
    message: String,
    fetchData: (() -> Unit)? = null
) {

    val coroutineScope = rememberCoroutineScope()
    val isRefreshing = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing.value)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing.value = true
                fetchData?.invoke()
                isRefreshing.value = false
            }
        }
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            errorCode?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 32.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Image(
                painter = painterResource(id = R.drawable.sad_boy_error),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 32.dp,
                        vertical = 32.dp
                    )
            )
        }
    }

}

@Preview
@Composable
private fun PrevErrorScreen() = GeminiSampleTheme {
    ErrorScreen(
        errorCode = "404",
        message = "Something went wrong!"
    )
}

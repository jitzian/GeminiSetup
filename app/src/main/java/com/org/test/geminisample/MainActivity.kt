package com.org.test.geminisample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.ai.client.generativeai.GenerativeModel
import com.org.test.geminisample.summary.ui.SummarizeRoute
import com.org.test.geminisample.summary.viewmodel.SummarizeViewModel
import com.org.test.geminisample.ui.theme.GeminiSampleTheme
import com.org.test.geminisample.weather.viewodel.WeatherViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GeminiSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-pro",
                        apiKey = BuildConfig.apiKey
                    )
                    //val summarizeViewModel = SummarizeViewModel()
                    //val weatherViewModel = WeatherViewModel()
                    SummarizeRoute(
                        //summarizeViewModel = summarizeViewModel,
                        //weatherViewModel = weatherViewModel,
                    )

                    // Use the injected WeatherViewModel
                }
            }
        }
    }
}



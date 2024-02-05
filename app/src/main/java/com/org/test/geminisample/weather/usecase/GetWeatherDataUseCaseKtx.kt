package com.org.test.geminisample.weather.usecase

fun generativePrompt(
    timeLocation1: String,
    windLocation1: String,
    timeLocation2: String,
    windLocation2: String,
) = """
            Given the following weather data:
            for location 1 the time/date is $timeLocation1 and the wind speed is $windLocation1
            and for location 2 the time/date is $timeLocation2 and the wind speed is $windLocation2
            generate a list separated by commas with the wind speed for the same days defined in $timeLocation1 and $timeLocation2 that 
            will be calculated based on an average of $windLocation1 and $windLocation2. The values should be in float format
        """.trimIndent()


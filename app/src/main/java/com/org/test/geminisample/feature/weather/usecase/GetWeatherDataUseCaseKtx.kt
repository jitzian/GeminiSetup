package com.org.test.geminisample.feature.weather.usecase

import com.org.test.geminisample.feature.weather.ui.MenuSelection

fun generativePrompt(
    timeLocation1: String,
    windLocation1: String,
    timeLocation2: String,
    windLocation2: String,
    selection: Int? = MenuSelection.SELECTION_1.param,
) = """
            Given the following weather data:
            for location 1 the time/date is $timeLocation1 and the wind speed is $windLocation1
            and for location 2 the time/date is $timeLocation2 and the wind speed is $windLocation2
            generate a list separated by commas with the wind speed for the same days defined in $timeLocation1 and $timeLocation2 that 
            will be calculated based on an average of $windLocation1 and $windLocation2. The values should be in float format.
            
            If $selection value is 1, then each element of the list should remain intact. Otherwise, if $selection value is 2, 
            then each element of the list should be rounded to the nearest integer + 11. Whereas, if $selection value is 3, 
            then each element of the list should be rounded to the nearest integer and then multiplied by 2.
            
        """.trimIndent()

//Sample of a response from the Gemini
val response =
    "6.2, 1.5, 3.8, 4.5, 7.4, 7.1, 4.7, 4.9, 5.1, 9.1, 8.5, 9.4, 11.5, 8.5, 8.9, 9.1, 13.1, 14.8, 9.6, 6.4, 9.2, 10.5, 10.9, 11.7, 12.6, 15.2, 12.4, 12.2, 10.7, 9.8, 9.6, 12.3, 12.4, 16.8, 16.8, 17.6, 18.4, 21.3, 18.2, 21.2, 20.2, 15.5, 7.7, 1.1, 9.4, 9.4, 12.1, 12.4, 11.2, 15.9, 19.8, 15.1, 13.3, 9.7, 11.2, 14.3, 12.1, 14.3, 15.6, 18.3, 17.0, 15.4, 16.4, 15.0, 15.4, 15.6, 13.4, 13.6, 12.4, 12.0, 11.1, 7.7, 7.2, 4.7, 7.6, 5.8, 6.8, 7.8, 7.7, 5.9, 4.9, 5.6, 7.6, 9.2, 5.0, 3.6, 1.8, 8.0, 11.7, 11.2, 6.4, 6.8, 8.2, 4.8, 3.6, 5.9, 5.2, 3.8, 7.2, 4.7, 6.2, 3.9, 5.1, 6.6, 4.3, 8.1, 8.2, 9.7, 10.4, 11.5, 11.6, 10.9, 16.9, 19.7, 16.2, 14.8, 14.2, 10.8, 9.7, 9.4, 5.4, 7.9, 9.2, 9.7, 7.9, 7.1, 8.4, 10.1, 9.0, 14.8, 14.2, 12.1, 14.4, 16.9, 17.4, 18.4, 18.1, 19.1, 18.4, 17.5, 17.1, 17.2, 17.2, 16.1, 15.7, 13.7, 13.2, 11.4, 12.1, 11.2, 12.6, 11.6, 10.2, 11.3, 11.2, 10.7, 9.3, 9.3, 10.8, 13.3, 10.0, 8.2, 6.7, 9.4, 8.3, 11.4, 9.8, 9.6, 9.8, 7.2, 7.8, 7.6, 7.1, 7.4, 7.3, 5.4, 3.5, 3.4, 8.9, 7.5, 6.6, 4.0, 5.1, 5.0, 4.1, 4.8, 3.4, 4.8, 6.1, 9.2, 6.0, 6.9, 7.3, 7.6, 8.3, 7.2, 7.6, 8.2, 8.0, 8.0, 8.1, 8.9, 10.5, 12.6, 13.7, 16.0, 18.8, 17.7, 19.5, 17.9, 16.6, 15.2, 15.4, 15.3, 14.8, 15.1, 16.2, 15.2, 15.0, 14.7, 15.8, 14.9, 15.1, 14.8, 16.9, 17.0, 18.1, 16.3, 17.3, 18.2, 18.1, 21.4, 22.1, 16.8, 16.5, 17.7, 16.6, 16.0, 17.1, 19.9, 19.3, 20.2, 20.4, 20.0, 19.4, 18.1, 16.3, 15.1, 14.8, 15.5, 16.8, 17.8, 18.7, 18.9, 17.7, 15.5, 13.5, 12.4, 11.3, 10.3, 9.3, 8.6, 8.5, 7.9, 7.2, 6.6, 6.4, 6.3, 6.5, 6.8, 6.8, 6.8, 6.5, 5.9, 6.6, 9.7, 13.9, 16.4, 14.7, 10.9, 9.0, 9.8, 11.3, 13.3, 15.2, 17.0, 18.7, 20.2, 6.2, 7.4, 6.6, 5.8, 6.9, 7.1, 6.3, 8.3, 10.0, 12.5, 13.5, 13.7, 13.5, 14.4, 13.0, 15.1, 18.7, 18.9, 20.6, 21.1, 22.2, 21.2, 22.2, 21.6, 22.9, 20.4, 22.2, 26.2, 24.5, 25.2, 25.6, 25.0, 21.2, 25.1, 25.4, 23.8, 24.2, 22.0, 23.5, 19.5, 13.7, 11.6, 10.0, 7.6, 7.7, 8.0, 8.9, 6.3, 5.9, 7.7, 10.2, 10.3, 12.2, 13.3, 12.1, 12.8, 12.2, 16.1, 16.2, 14.9, 13.6, 9.8, 8.4, 10.8, 10.4, 6.6, 6.6, 5.9, 7.7, 9.0, 10.9, 10.2, 9.7, 8.3, 7.4, 6.6, 6.6, 6.2, 5.2, 4.7,"
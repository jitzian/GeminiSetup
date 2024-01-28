package com.org.test.geminisample.remote.rest.model

data class ResultApi(
    val elevation: Int = 0,
    val generationTimeMs: Double = 0.0,
    val hourly: Hourly = Hourly(),
    val hourlyUnits: HourlyUnits = HourlyUnits(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val timezone: String = "",
    val timezoneAbbreviation: String = "",
    val utcOffsetSeconds: Int = 0
)
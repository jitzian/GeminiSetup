package com.org.test.geminisample.remote.rest.model

data class Hourly(
    val relative_humidity_2m: List<Int> = listOf(),
    val temperature_2m: List<Double> = listOf(),
    val time: List<String> = listOf(),
    val wind_speed_10m: List<Double> = listOf()
)
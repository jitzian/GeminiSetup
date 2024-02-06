package com.org.test.geminisample.data.model

import co.yml.charts.common.model.Point

data class LocationsData(
    val pointsDataLocation1: List<Point> = emptyList(),
    val pointsDataLocation2: List<Point> = emptyList(),
    val pointsDataProjection: List<Point> = emptyList(),
    val dates: List<String> = emptyList(),
)

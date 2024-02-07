package com.org.test.geminisample.weather.mapper

import co.yml.charts.common.model.Point
import javax.inject.Inject

class WindLocationToListPointMapper @Inject constructor() : (List<String>) -> (List<Point>) {
    override fun invoke(input: List<String>): List<Point> =
        input.mapIndexed { index, s -> Point(index.toFloat(), s.toFloat()) }
}
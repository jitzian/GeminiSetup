package com.org.test.geminisample.weather.mapper

import co.yml.charts.common.model.Point
import javax.inject.Inject

class WindLocationToListPointMapper @Inject constructor() : (List<String>) -> (List<Point>) {
    override fun invoke(input: List<String>): List<Point> {
        val points = mutableListOf<Point>()
        for (i in input.indices) {
            points.add(Point(i.toFloat(), input[i].toFloat()))
        }
        return points
    }
}
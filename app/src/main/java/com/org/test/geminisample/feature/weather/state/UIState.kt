package com.org.test.geminisample.feature.weather.state

import co.yml.charts.common.model.Point

sealed interface UIState {
    data object Initial : UIState
    data class Loading(val message: String? = null) : UIState
    data class Success(
        val isLoading: Boolean = false,
        val pointsDataLocation1: List<Point> = emptyList(),
        val pointsDataLocation2: List<Point> = emptyList(),
        val pointsDataProjection: List<Point> = emptyList(),
        val dates: List<String> = emptyList(),
    ) : UIState

    data class Error(
        val errorMessage: String
    ) : UIState
}

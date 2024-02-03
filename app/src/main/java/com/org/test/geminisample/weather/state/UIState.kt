package com.org.test.geminisample.weather.state

import com.org.test.geminisample.data.model.LocationData

sealed interface UIState {
    data object Initial : UIState
    data class Loading(val message: String? = null) : UIState
    data class Success(
        val location1Data: LocationData = LocationData(emptyList(), emptyList()),
        val location2Data: LocationData = LocationData(emptyList(), emptyList()),
        val projectionData: LocationData? = null,
    ) : UIState

    data class Error(
        val errorMessage: String
    ) : UIState
}

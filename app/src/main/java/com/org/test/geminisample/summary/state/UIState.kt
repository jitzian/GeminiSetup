package com.org.test.geminisample.summary.state

sealed interface UIState {
    data object Initial : UIState
    data object Loading : UIState
    data class Success(val message: String) : UIState
    data class Error(val errorMessage: String) : UIState
}

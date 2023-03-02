package ca.qc.cstj.remotedatasource.presentation.main

import ca.qc.cstj.remotedatasource.domain.models.Planet

sealed class MainUiState {
    object Empty : MainUiState()
    object Loading : MainUiState()
    class Success(val planets: List<Planet>): MainUiState()
    class Error(val exception: Exception) : MainUiState()
}
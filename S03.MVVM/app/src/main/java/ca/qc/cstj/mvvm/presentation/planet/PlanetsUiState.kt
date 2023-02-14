package ca.qc.cstj.mvvm.presentation.planet

import ca.qc.cstj.mvvm.domain.models.Planet

sealed class PlanetsUiState {
    object Empty : PlanetsUiState()
    class Success(val planets: List<Planet>) : PlanetsUiState()
}
package ca.qc.cstj.s09navigationdrawer.ui.planet.detail

import ca.qc.cstj.s09navigationdrawer.domain.models.Planet

sealed class PlanetUiState {
    class Error(val exception: Exception) : PlanetUiState()
    class Success(val planet: Planet) : PlanetUiState()
    object Empty : PlanetUiState()
}

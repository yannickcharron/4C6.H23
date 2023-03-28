package ca.qc.cstj.s09navigationdrawer.ui.planet

import ca.qc.cstj.s09navigationdrawer.domain.models.Planet

sealed class PlanetListUiState {
    object Loading: PlanetListUiState()
    class Success(val planets: List<Planet>): PlanetListUiState()
    class Error(val exception: Exception? = null) : PlanetListUiState()
}
package ca.qc.cstj.mvvm.presentation.planet

import androidx.lifecycle.ViewModel
import ca.qc.cstj.mvvm.data.repositories.PlanetRepository
import ca.qc.cstj.mvvm.presentation.main.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlanetsViewModel : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _planetsUiState = MutableStateFlow<PlanetsUiState>(PlanetsUiState.Empty)
    val planetsUiState = _planetsUiState.asStateFlow()

    init {
        _planetsUiState.update {
            val planets = planetRepository.retrievePlanets()
            return@update PlanetsUiState.Success(planets)
        }

        // Le code lu ligne par ligne
        // val planets = planetRepository.retrievePlanets()
        // val state = PlanetsUiState.Success(planets)
        // _planetsUiState.update(state)

    }



}
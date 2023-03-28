package ca.qc.cstj.s09navigationdrawer.ui.planet


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s09navigationdrawer.core.ApiResult
import ca.qc.cstj.s09navigationdrawer.data.repositories.PlanetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetListViewModel : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _mainUiState = MutableStateFlow<PlanetListUiState>(PlanetListUiState.Loading)
    val mainUiState = _mainUiState.asStateFlow()

    init {
        refreshPlanets()
    }

    fun refreshPlanets() {
        viewModelScope.launch {
            planetRepository.retrieveAll().collect {
                _mainUiState.update { _ ->
                    when (it) {
                        is ApiResult.Error -> PlanetListUiState.Error(it.throwable as Exception)
                        ApiResult.Loading -> PlanetListUiState.Loading
                        is ApiResult.Success -> PlanetListUiState.Success(it.data)
                    }
                }
            }
        }
    }

}
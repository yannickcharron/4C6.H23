package ca.qc.cstj.s09navigationdrawer.ui.planet.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s09navigationdrawer.core.ApiResult

import ca.qc.cstj.s09navigationdrawer.data.repositories.PlanetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetViewModel(private val href: String) : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _planetUiState = MutableStateFlow<PlanetUiState>(PlanetUiState.Empty)
    val planetUiState = _planetUiState.asStateFlow()

    init {
        viewModelScope.launch {
            planetRepository.retrieveOne(href).collect { apiResult ->
                _planetUiState.update { _ ->
                    when(apiResult) {
                        is ApiResult.Error -> PlanetUiState.Error(apiResult.throwable as Exception)
                        ApiResult.Loading -> PlanetUiState.Empty
                        is ApiResult.Success -> PlanetUiState.Success(apiResult.data)
                    }

                }
            }
        }
    }

    class Factory(private val href: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(href)
        }
    }

}
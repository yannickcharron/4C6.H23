package ca.qc.cstj.remotedatasource.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.remotedatasource.core.ApiResult
import ca.qc.cstj.remotedatasource.data.repositories.MemoryPlanetRepository
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import ca.qc.cstj.remotedatasource.domain.models.Planet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Empty)
    val mainUiState = _mainUiState.asStateFlow()

    init {
        viewModelScope.launch {
            planetRepository.retrieveAllWithoutRefresh().collect { apiResult ->
                _mainUiState.update {
                    when(apiResult) {
                        is ApiResult.Error -> MainUiState.Error(apiResult.throwable as Exception)
                        ApiResult.Loading -> MainUiState.Loading
                        is ApiResult.Success -> MainUiState.Success(apiResult.data)
                    }
                }
            }
        }

    }

}
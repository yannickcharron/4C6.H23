package ca.qc.cstj.meteomania.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.meteomania.core.ApiResult
import ca.qc.cstj.meteomania.data.repositories.MeteoRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val meteoRepository = MeteoRepository()

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Empty)
    val searchUiState = _searchUiState.asStateFlow()

    private var searchJob : Job? = null

    fun search(cityName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if(cityName.isNotBlank()) {
                meteoRepository.retrieveByCityName(cityName).collect { apiResult ->
                    _searchUiState.update {
                        when(apiResult) {
                            is ApiResult.Error -> SearchUiState.Error(apiResult.exception)
                            ApiResult.Loading -> SearchUiState.Loading
                            is ApiResult.Success -> SearchUiState.Success(apiResult.data)
                        }
                    }
                }
            }
        }
    }

}
package ca.qc.cstj.meteomania.ui.search

import ca.qc.cstj.meteomania.domain.models.Meteo

// Des fois pas Empty ou pas Loading
// Dans Success le type du paramètre et le nombre
sealed class SearchUiState {
    object Empty : SearchUiState()
    object Loading : SearchUiState()
    class Success(val meteo: Meteo): SearchUiState()
    class Error(val exception: Exception? = null) : SearchUiState()
}
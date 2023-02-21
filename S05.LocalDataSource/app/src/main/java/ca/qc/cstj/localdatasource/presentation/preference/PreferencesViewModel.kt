package ca.qc.cstj.localdatasource.presentation.preference


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class PreferencesViewModel : ViewModel() {

    private val _preferencesUiState = MutableStateFlow<PreferencesUiState>(PreferencesUiState.Empty)
    val preferencesUiState = _preferencesUiState.asStateFlow()

}
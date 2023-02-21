package ca.qc.cstj.localdatasource.presentation.preference

import ca.qc.cstj.localdatasource.domain.models.UserPreferences

sealed class PreferencesUiState {
    class Success(val userPreferences: UserPreferences) : PreferencesUiState()
    object Empty : PreferencesUiState()
}
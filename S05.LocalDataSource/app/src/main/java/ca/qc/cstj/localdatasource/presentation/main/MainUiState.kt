package ca.qc.cstj.localdatasource.presentation.main

import ca.qc.cstj.localdatasource.domain.models.Note
import ca.qc.cstj.localdatasource.domain.models.UserPreferences

sealed class MainUiState {
    class Success(val notes: List<Note>, val userPreferences: UserPreferences) : MainUiState()
    object Empty : MainUiState()
}
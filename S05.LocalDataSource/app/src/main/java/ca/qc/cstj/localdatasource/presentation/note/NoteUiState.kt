package ca.qc.cstj.localdatasource.presentation.note

sealed class NoteUiState {
    object Empty : NoteUiState()
    object Completed : NoteUiState()
}
package ca.qc.cstj.mvvm.presentation.main

import ca.qc.cstj.mvvm.domain.models.Pilot

//class PilotUiState(val isSuccess: Boolean, val pilot: Pilot)

sealed class MainUiState {
    class Success(val pilot: Pilot): MainUiState()
    object Loading: MainUiState() //N'est pas toujours présente
    class Error(val message: String): MainUiState()
    object Empty: MainUiState() //N'est pas toujours présente
}

//enum class TestState {
//    Success,
//    Loading,
//    Error,
//    Empty
//}

// isError => pilot == null
// isSuccess => pilot == Pilot()
// isLoading => pilot == null
// isEmpty => pilot == null
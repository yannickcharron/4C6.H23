package ca.qc.cstj.localdatasource.presentation.main

import androidx.lifecycle.ViewModel
import ca.qc.cstj.localdatasource.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    //private val noteRepository = NoteRepository()

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Empty)
    val mainUiState = _mainUiState.asStateFlow()

    init {
//        val notes = noteRepository.retrieveAll()
//        _mainUiState.update {
//            return@update MainUiState.Success(notes)
//        }
    }

}
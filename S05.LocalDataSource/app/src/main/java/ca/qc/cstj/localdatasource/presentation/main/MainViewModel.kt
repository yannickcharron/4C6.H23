package ca.qc.cstj.localdatasource.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.localdatasource.core.AppDatabase
import ca.qc.cstj.localdatasource.domain.models.Note
import ca.qc.cstj.localdatasource.domain.models.UserPreferences
import ca.qc.cstj.localdatasource.domain.repositories.NoteRepository
import ca.qc.cstj.localdatasource.domain.repositories.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val noteRepository = AppDatabase.getInstance(application).noteRepository()
    private val userPreferencesRepository = UserPreferencesRepository(application)

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Empty)
    val mainUiState = _mainUiState.asStateFlow()

    init {
        var notes: List<Note> = listOf()
        var preferences = UserPreferences()

        //1er Thread démarré
        viewModelScope.launch {
            noteRepository.retrieveAll().collect {  //notes = kayak
                notes = it
                _mainUiState.update {
                    return@update MainUiState.Success(notes, preferences)
                }
            }
        }

        //2e Thread démarré
        viewModelScope.launch {
            userPreferencesRepository.userPreferences.collect {
                preferences = it
                _mainUiState.update {
                    return@update MainUiState.Success(notes, preferences)
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.delete(note)
        }
    }

}
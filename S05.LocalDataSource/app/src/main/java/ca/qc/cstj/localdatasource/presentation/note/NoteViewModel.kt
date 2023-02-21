package ca.qc.cstj.localdatasource.presentation.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.localdatasource.core.AppDatabase
import ca.qc.cstj.localdatasource.domain.models.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(application : Application) : AndroidViewModel(application) {

    private val noteRepository = AppDatabase.getInstance(application).noteRepository()

    private val _noteUiState = MutableStateFlow<NoteUiState>(NoteUiState.Empty)
    val noteUiState = _noteUiState.asStateFlow()


    fun saveNote(title: String, content: String, color: String) {
        viewModelScope.launch {
            val note = Note(title, content, color)
            noteRepository.insert(note)
            _noteUiState.update {
                NoteUiState.Completed
            }
        }
    }

}
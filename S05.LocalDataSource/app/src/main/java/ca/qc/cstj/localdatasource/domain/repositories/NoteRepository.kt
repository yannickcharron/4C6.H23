package ca.qc.cstj.localdatasource.domain.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ca.qc.cstj.localdatasource.core.Constants
import ca.qc.cstj.localdatasource.domain.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteRepository {

//    fun retrieveAll() : List<Note> {
//        //TODO: Genérer les notes
//        var number = 0
//        val notes =  generateSequence {
//            (Note("Note ${++number}", "Contenu $number", Constants.COLORS.random()))
//                .takeIf { number <= Constants.NUMBER_OF_NOTES }
//        }
//
//        return notes.toList()
//    }

    @Query("SELECT * FROM notes")
    fun retrieveAll() : Flow<List<Note>>

    @Insert
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAll()

}

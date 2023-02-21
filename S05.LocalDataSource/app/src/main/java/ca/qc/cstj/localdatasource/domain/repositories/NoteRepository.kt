package ca.qc.cstj.localdatasource.domain.repositories

import ca.qc.cstj.localdatasource.core.Constants
import ca.qc.cstj.localdatasource.domain.models.Note

class NoteRepository {

    fun retrieveAll() : List<Note> {
        //TODO: Genérer les notes
        var number = 0
        val notes =  generateSequence {
            (Note("Note ${++number}", "Contenu $number", Constants.COLORS.random()))
                .takeIf { number <= Constants.NUMBER_OF_NOTES }
        }

        return notes.toList()


    }

}

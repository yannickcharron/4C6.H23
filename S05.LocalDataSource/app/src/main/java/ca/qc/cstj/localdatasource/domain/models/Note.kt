package ca.qc.cstj.localdatasource.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class Note(val title: String, val content: String, val color: String) {

    @PrimaryKey(autoGenerate = true)
    var idNote : Int = 0
}


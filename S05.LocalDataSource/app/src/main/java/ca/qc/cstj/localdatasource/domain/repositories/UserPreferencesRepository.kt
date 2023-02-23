package ca.qc.cstj.localdatasource.domain.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import ca.qc.cstj.localdatasource.core.dataStore
import ca.qc.cstj.localdatasource.domain.models.UserPreferences
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(private val context: Context) {

    object PreferencesKeys {
        val USERNAME = stringPreferencesKey("username")
        val IS_DARK_MODE_ON = booleanPreferencesKey("isDarkModeOn")
        //POUR le TP Attention ici c'est une réponse
        //val QUANTITY_ELEMENT_YE = floatPreferencesKey("element_ye") X 5
    }

    val userPreferences = context.dataStore.data.map {
        val name = it[PreferencesKeys.USERNAME] ?: ""
        val isDarkModeOn = it[PreferencesKeys.IS_DARK_MODE_ON] ?: false
        return@map UserPreferences(name, isDarkModeOn)
    }


    suspend fun save(name: String, isDarkModeOn : Boolean) {
        context.dataStore.edit {
            it[PreferencesKeys.USERNAME] = name
            it[PreferencesKeys.IS_DARK_MODE_ON] = isDarkModeOn
        }
    }

    suspend fun saveName(name: String) {
        context.dataStore.edit {
            it[PreferencesKeys.USERNAME] = name
        }
    }

    suspend fun saveDarkMode(isDarkModeOn : Boolean) {
        context.dataStore.edit {
            it[PreferencesKeys.IS_DARK_MODE_ON] = isDarkModeOn
        }
    }





}
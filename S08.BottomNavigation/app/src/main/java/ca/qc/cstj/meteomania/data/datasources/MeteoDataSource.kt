package ca.qc.cstj.meteomania.data.datasources

import ca.qc.cstj.meteomania.core.Constants
import ca.qc.cstj.meteomania.core.JsonDataSource
import ca.qc.cstj.meteomania.domain.dto.MeteoDTO
import ca.qc.cstj.meteomania.domain.models.Meteo
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString

class MeteoDataSource : JsonDataSource(){

    fun retrieveOne(cityName: String) : Meteo {

        val serviceURL = "${Constants.URL_BASE}?q=$cityName&appid=${Constants.WEATHER_API_KEY}&units=metric"

        val (_, _, result) = serviceURL.httpGet().responseJson()

        return when(result) {
            is Result.Success -> {
                val meteoDTO = json.decodeFromString<MeteoDTO>(result.value.content)
                Meteo(meteoDTO)
            }
            is Result.Failure -> throw result.error.exception
        }

    }

}
package ca.qc.cstj.remotedatasource.data.datasources

import ca.qc.cstj.remotedatasource.core.ApiResult
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.core.JsonDataSource
import ca.qc.cstj.remotedatasource.domain.models.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString
import org.json.JSONObject

class PlanetDataSource : JsonDataSource() {

    fun retrieveAll() : ApiResult<List<Planet>> {
        val (_, _, result)  = Constants.BaseUrl.PLANETS.httpGet().responseJson()

        return when(result) {
            is Result.Success -> ApiResult.Success(json.decodeFromString(result.value.content))
            is Result.Failure -> ApiResult.Error(result.error.exception)
        }
    }

    fun retrieveAllManual() : ApiResult<List<Planet>> {

        //Fait un GET HTTP sur l'URL contenu dans la constante : Constants.BaseUrl.PLANETS
        val (_, _, result)  = Constants.BaseUrl.PLANETS.httpGet().responseJson()

        return when(result) {
            is Result.Success -> {
                val jsonPlanets = result.value.array() //la réponse du serveur en JSON
                val planets = mutableListOf<Planet>() //la liste d'objet de mon modèle
                for(i in 0 until jsonPlanets.length()) {
                    planets.add(deserializePlanet(jsonPlanets.getJSONObject(i)))
                }

                ApiResult.Success(planets)
            }
            is Result.Failure -> ApiResult.Error(result.error.exception)
        }

    }

    private fun deserializePlanet(planetJson : JSONObject) : Planet {
        val name = planetJson.getString("name")
        val temperature = planetJson.getDouble("temperature")
        val image = planetJson.getString("icon")

        return Planet(name, image, temperature)

    }

}
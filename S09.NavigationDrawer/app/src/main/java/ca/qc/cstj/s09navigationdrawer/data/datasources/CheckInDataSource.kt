package ca.qc.cstj.s09navigationdrawer.data.datasources

import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.core.JsonDataSource
import ca.qc.cstj.s09navigationdrawer.domain.models.CheckIn
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class CheckInDataSource : JsonDataSource() {

    fun create(checkIn: CheckIn) : CheckIn {
        //Mettre en JSON
        val body = json.encodeToString(checkIn)

        //Envoie au serveur avec un POST
        val (_, _, result) = Constants.BaseURL.CHECKIN_URL.httpPost().jsonBody(body).responseJson()

        //Gérer la réponse
        return when(result) {
            is Result.Success -> json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error.exception
        }

    }

}

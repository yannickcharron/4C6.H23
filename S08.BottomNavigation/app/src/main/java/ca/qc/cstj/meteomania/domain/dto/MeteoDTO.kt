package ca.qc.cstj.meteomania.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeteoDTO(
    val name: String,
    val coord: Coord,
    val main: Main,
    val weather : List<Weather>,
    val sys: Sys,
    @SerialName("dt")
    val datetime : Int,
    val timezone : Int
) {

    @Serializable
    data class Sys(
        val country : String,
        val id: Int,
        val sunrise: Int,
        val sunset: Int,
        val type: Int
    )

    @Serializable
    data class Coord(
        val lon : Double,
        val lat: Double
    )

    @Serializable
    data class Main(
        val feels_like: Double,
        val humidity: Int,
        val pressure: Int,
        val temp: Double,
        val temp_max: Double,
        val temp_min: Double
    )

    @Serializable
    data class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String
    )
}
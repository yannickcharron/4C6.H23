package ca.qc.cstj.meteomania.domain.models

import ca.qc.cstj.meteomania.domain.dto.MeteoDTO

class Meteo(meteoDTO: MeteoDTO) {
    val city : String = meteoDTO.name
    val country : String = meteoDTO.sys.country
    val temperature: Double = meteoDTO.main.temp
    val weather : String = meteoDTO.weather[0].main
    val latitude : Double = meteoDTO.coord.lat
    val longitude : Double = meteoDTO.coord.lon
    val timestamp : Int = meteoDTO.datetime
    val timezone : Int = meteoDTO.timezone
}

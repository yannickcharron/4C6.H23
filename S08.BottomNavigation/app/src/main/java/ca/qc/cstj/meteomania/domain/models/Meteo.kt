package ca.qc.cstj.meteomania.domain.models

data class Meteo(
    val city : String,
    val country : String,
    val temperature: Double,
    val weather : String,
    val latitude : Double,
    val longitude : Double
    //TODO : Ajout pour les deux dates (serveur/local)
)

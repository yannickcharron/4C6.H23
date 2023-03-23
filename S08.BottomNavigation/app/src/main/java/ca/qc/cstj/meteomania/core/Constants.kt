package ca.qc.cstj.meteomania.core

object Constants {
    object RefreshDelay {
        const val METEO_REFRESH = 1000L * 60
    }

    const val TEMPERATURE_LOWER_BOUND = 10
    const val WEATHER_API_KEY = "6b4153bdef04f183a43427c9143b624e"
    const val URL_BASE = "https://api.openweathermap.org/data/2.5/weather"
    const val COUNTRY_IMAGE_API = "https://flagcdn.com/w80/%s.png"

}
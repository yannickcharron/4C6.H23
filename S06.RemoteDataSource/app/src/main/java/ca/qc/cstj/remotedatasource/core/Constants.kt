package ca.qc.cstj.remotedatasource.core

object Constants {
    const val IMAGE_URL = "https://assets.andromia.science/planets/%s.png"

    object BaseUrl {
        private const val BASE_API = "https://api.andromia.science"
        const val PLANETS = "$BASE_API/planets"
    }

    object RefreshDelay {
        const val DEFAULT = 60000L
        const val PLANETS_LIST = 30000L
    }

}
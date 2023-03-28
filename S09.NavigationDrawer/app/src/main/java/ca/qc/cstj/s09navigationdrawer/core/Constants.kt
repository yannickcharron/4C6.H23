package ca.qc.cstj.s09navigationdrawer.core

object Constants {

    object BaseURL {
        private const val BASE_API = "https://api.andromia.science"
        const val PLANETS = "${BASE_API}/planets"
    }

    object RefreshDelay {
        const val PLANET_DELAY: Long = 120000L
    }


}
package ca.qc.cstj.s09navigationdrawer.core

object Constants {

    object BaseURL {
        private const val BASE_API = "https://api.andromia.science"
        const val PLANETS = "${BASE_API}/planets"
        const val CHECKIN_URL = "${BASE_API}/check-ins"
    }

    object RefreshDelay {
        const val PLANET_DELAY: Long = 120000L
    }

    const val DOOR = 7
    const val VIDEO_URL = "http://www.ebookfrenzy.com/android_book/movie.mp4"

}
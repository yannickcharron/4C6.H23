package ca.qc.cstj.remotedatasource.data.repositories

import ca.qc.cstj.remotedatasource.domain.models.Planet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class MemoryPlanetRepository {

    companion object {
        const val PLANET_REFRESH_DELAY = 5000L
    }

    private val _planets = mutableListOf<Planet>()

    fun retrieveAll() : Flow<List<Planet>> {

        return flow {
            while(true) {
                randomPlanets()
                emit(_planets)
                delay(PLANET_REFRESH_DELAY)
            }
        }
    }

    private fun randomPlanets() {
        if(_planets.isEmpty()) {
            val numberToGenerate = Random.nextInt(5, 25)
            repeat(numberToGenerate) {
                _planets.add(createPlanet())
            }
        } else {
            _planets.forEach {
                it.image = Random.nextInt(1,7).toString()
                it.temperature = Random.nextDouble(-50.0, 50.0)
            }
        }
    }


//    fun retrievePlanets() : List<Planet> {
//        val planets = mutableListOf<Planet>()
//        val numberToGenerate = Random.nextInt(5, 25)
//
//        for (i in 0 .. numberToGenerate) {
//            planets.add(createPlanet())
//        }
//
//        return planets
//    }

    private fun createPlanet() : Planet{
        val planetNumber = Random.nextInt(1,50)
        val planetImage = Random.nextInt(1,7)
        val planetTemperature = Random.nextDouble(-50.0, 50.0)

        return Planet("Planet$planetNumber", planetImage.toString(), planetTemperature)
    }


}
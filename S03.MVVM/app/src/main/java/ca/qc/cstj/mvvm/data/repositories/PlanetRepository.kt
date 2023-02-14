package ca.qc.cstj.mvvm.data.repositories

import ca.qc.cstj.mvvm.domain.models.Planet
import kotlin.random.Random

class PlanetRepository {

    fun retrievePlanets() : List<Planet> {
        val planets = mutableListOf<Planet>()
        val numberToGenerate = Random.nextInt(5, 25)

        for (i in 0 .. numberToGenerate) {
            planets.add(createPlanet())
        }

        return planets
    }

    private fun createPlanet() : Planet{
        val planetNumber = Random.nextInt(1,50)
        val planetImage = Random.nextInt(1,7)
        val planetTemperature = Random.nextDouble(-50.0, 50.0)

        return Planet("Planet$planetNumber", planetTemperature, planetImage.toString())
    }

}
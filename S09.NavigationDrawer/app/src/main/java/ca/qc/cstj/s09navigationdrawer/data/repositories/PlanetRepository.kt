package ca.qc.cstj.s09navigationdrawer.data.repositories

import ca.qc.cstj.s09navigationdrawer.core.ApiResult
import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.data.datasources.PlanetDataSource
import ca.qc.cstj.s09navigationdrawer.domain.models.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlanetRepository {

    private val planetDataSource = PlanetDataSource()

    fun retrieveAll() : Flow<ApiResult<List<Planet>>> {
        return flow {
            while(true) {
                emit(ApiResult.Loading)
                try {
                    emit(ApiResult.Success(planetDataSource.retrieveAll()))
                } catch (ex:Exception) {
                    emit(ApiResult.Error(ex))
                }
                delay(Constants.RefreshDelay.PLANET_DELAY)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun retrieveOne(href: String) : Flow<ApiResult<Planet>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(planetDataSource.retrieveOne(href)))
            } catch(ex: Exception) {
                emit(ApiResult.Error(ex))
            }

        }.flowOn(Dispatchers.IO)
    }
}
package ca.qc.cstj.remotedatasource.data.repositories

import ca.qc.cstj.remotedatasource.core.ApiResult
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource
import ca.qc.cstj.remotedatasource.domain.models.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

class PlanetRepository {

    private val planetDataSource = PlanetDataSource()

    fun retrieveAll() : Flow<ApiResult<List<Planet>>> {
        return flow {
            while(true) {
                emit(ApiResult.Loading)
                //delay(5000L)
                emit(planetDataSource.retrieveAll())
                delay(Constants.RefreshDelay.PLANETS_LIST)
            }

        }.flowOn(Dispatchers.IO)
    }

    fun retrieveAllWithoutRefresh() : Flow<ApiResult<List<Planet>>> {
        return flow {
            emit(ApiResult.Loading)
            emit(planetDataSource.retrieveAll())
        }.flowOn(Dispatchers.IO)
    }

}
package ca.qc.cstj.remotedatasource.data.repositories

import ca.qc.cstj.remotedatasource.core.ApiResult
import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource
import ca.qc.cstj.remotedatasource.domain.models.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

class PlanetRepository {

    private val planetDataSource = PlanetDataSource()

    fun retrieveAll() {

    }

    fun retrieveAllWithoutRefresh() : Flow<ApiResult<List<Planet>>> {
        return flow {
            emit(ApiResult.Loading)
            emit(planetDataSource.retrieveAll())
        }.flowOn(Dispatchers.IO)
    }

}
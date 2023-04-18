package ca.qc.cstj.s09navigationdrawer.data.repositories

import ca.qc.cstj.s09navigationdrawer.core.ApiResult
import ca.qc.cstj.s09navigationdrawer.data.datasources.CheckInDataSource
import ca.qc.cstj.s09navigationdrawer.domain.models.CheckIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CheckInRepository {

    private val checkInDataSource = CheckInDataSource()

    fun create(checkIn: CheckIn) : Flow<ApiResult<CheckIn>> {
        return flow {
            //Loading possible
            try {
                emit(ApiResult.Success(checkInDataSource.create(checkIn)))
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }

}

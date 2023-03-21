package ca.qc.cstj.meteomania.data.repositories

import ca.qc.cstj.meteomania.core.ApiResult
import ca.qc.cstj.meteomania.core.Constants
import ca.qc.cstj.meteomania.data.datasources.MeteoDataSource
import ca.qc.cstj.meteomania.domain.models.Meteo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MeteoRepository {

    private val meteoRepository = MeteoDataSource()

    //Possibilité de type de retour => 1<2<3>>, 2<3>, 1<3>,  3
    //1. Flow, si on veut une mise à jour régulière
    //2. ApiResult si notre DataSource fait un appel à un service
    //3. L'objet de notre modèle, les données nécessaire au viewmodel
    fun retrieveByCityName(cityName: String) : Flow<ApiResult<Meteo>> {

        return flow {
            while(true) {
                try {
                    emit(ApiResult.Loading)
                    emit(ApiResult.Success(meteoRepository.retrieveOne(cityName)))
                } catch (e: Exception) {
                    emit(ApiResult.Error(e))
                }
                delay(Constants.RefreshDelay.METEO_REFRESH)

            }
        }.flowOn(Dispatchers.IO)

    }

}
package net.taraabar.challengecode.data.repository

import kotlinx.coroutines.flow.Flow
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.network.base.CustomResult

interface ITaraabarRepository {

    suspend fun acceptCargo(): Flow<CustomResult<Boolean>>
    suspend fun getMockCargoList(): Flow<CustomResult<List<CargoResponse>>>

}

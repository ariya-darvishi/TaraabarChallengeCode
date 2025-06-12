package net.taraabar.challengecode.data.repository

import kotlinx.coroutines.flow.Flow
import net.taraabar.challengecode.data.remote.model.response.ShipmentResponse
import net.taraabar.network.base.CustomResult

interface ITaraabarRepository {

    suspend fun submitShipment(): Flow<CustomResult<Boolean>>
    suspend fun getMockShipmentList(): Flow<CustomResult<List<ShipmentResponse>>>

}

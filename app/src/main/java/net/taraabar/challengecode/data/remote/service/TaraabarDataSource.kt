package net.taraabar.challengecode.data.remote.service


import net.taraabar.challengecode.data.remote.model.response.ShipmentResponse
import net.taraabar.challengecode.data.remote.model.response.ShipmentResponseListMockData
import net.taraabar.network.base.CustomResult
import net.taraabar.network.base.withResponse
import javax.inject.Inject

class TaraabarDataSource @Inject constructor(
    private val service: TaraabarService,
) {

    suspend fun submitShipment() = withResponse { service.submitShipment() }

    suspend fun getMockShipmentList(): CustomResult<List<ShipmentResponse>> {
        return CustomResult.Success(ShipmentResponseListMockData.MOCK_DATA.items)
    }


}
package net.taraabar.challengecode.data.remote.service


import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseListMockData
import net.taraabar.network.base.CustomResult
import net.taraabar.network.base.withResponse
import javax.inject.Inject

class TaraabarDataSource @Inject constructor(
    private val service: TaraabarService,
) {

    suspend fun acceptCargo() = withResponse { service.acceptCargo() }

    suspend fun getMockCargoList(): CustomResult<List<CargoResponse>> {
        return CustomResult.Success(CargoResponseListMockData.MOCK_DATA.items)
    }


}
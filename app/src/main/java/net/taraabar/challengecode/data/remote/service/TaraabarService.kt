package net.taraabar.challengecode.data.remote.service


import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TaraabarService {


    @POST("shipment/submit")
    suspend fun acceptCargo(): Response<Boolean>

    @GET("shipment/list")
    suspend fun getCargoList(): Response<List<CargoResponse>>
}
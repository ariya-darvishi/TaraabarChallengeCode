package net.taraabar.challengecode.data.remote.service


import net.taraabar.challengecode.data.remote.model.response.ShipmentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TaraabarService {


    @POST("shipment/submit")
    suspend fun submitShipment(): Response<Boolean>

    @GET("shipment/list")
    suspend fun getShipmentList(): Response<List<ShipmentResponse>>
}
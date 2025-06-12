package net.taraabar.challengecode.ui.shipment

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.StateFlow
import net.taraabar.challengecode.data.remote.model.response.ShipmentResponse
import net.taraabar.challengecode.data.remote.model.response.ShipmentResponseList
import net.taraabar.network.states.ApiCallState


@Stable
@Immutable
data class ShipmentStateHolder(
    val shipmentList: StateFlow<ShipmentResponseList>,
    val currentShipmentItemSelected: StateFlow<ShipmentResponse>,
    val getShipmentListApiCallState: ApiCallState = ApiCallState(),
    val isLoadingShipmentList: StateFlow<Boolean>,
    val isLoadingShipmentItemDetail: StateFlow<Boolean>,
    val showItemDetailBottomSheet: StateFlow<Boolean>,

)
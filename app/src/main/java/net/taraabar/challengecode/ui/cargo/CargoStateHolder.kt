package net.taraabar.challengecode.ui.cargo

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.StateFlow
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseList
import net.taraabar.network.states.ApiCallState


@Stable
@Immutable
data class CargoStateHolder(
    val cargoList: StateFlow<CargoResponseList>,
    val currentCargoSelected: StateFlow<CargoResponse?>,
    val getCargoListApiCallState: ApiCallState = ApiCallState(),
    val isLoadingCargoList: StateFlow<Boolean>,
    val isLoadingCargoItemDetail: StateFlow<Boolean>,
    val showCargoDetailBottomSheet: StateFlow<Boolean>,

    )
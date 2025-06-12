package net.taraabar.challengecode.ui.cargo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseList
import net.taraabar.challengecode.data.repository.ITaraabarRepository
import net.taraabar.challengecode.utils.CargoStatus
import net.taraabar.challengecode.utils.updateSelectionStatus
import net.taraabar.network.states.apiResultCollector
import javax.inject.Inject


@HiltViewModel
class CargoViewModel @Inject constructor(
    private val repository: ITaraabarRepository,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _cargoList = MutableStateFlow(CargoResponseList(emptyList()))
    private val _currentCargoSelected = MutableStateFlow(CargoResponse())
    private val _isLoadingCargoList = MutableStateFlow(false)
    private val _isLoadingCargoDetail = MutableStateFlow(false)
    private val _showCargoDetailBottomSheet = MutableStateFlow(false)

    val states = CargoStateHolder(
        cargoList = _cargoList.asStateFlow(),
        currentCargoSelected = _currentCargoSelected.asStateFlow(),
        isLoadingCargoList = _isLoadingCargoList.asStateFlow(),
        isLoadingCargoItemDetail = _isLoadingCargoDetail.asStateFlow(),
        showCargoDetailBottomSheet = _showCargoDetailBottomSheet.asStateFlow(),
    )

    val events = object : CargoEvents {

        override fun initCargoScreen() {
            viewModelScope.launch {
                _isLoadingCargoList.value = true
                delay(1000)
                getShipmentList()
            }

        }

        override fun onCargoItemClick(item: CargoResponse) {
            _currentCargoSelected.value = item
            _showCargoDetailBottomSheet.value = true
            viewModelScope.launch {
                _isLoadingCargoDetail.value = true
                delay(1000)
                _isLoadingCargoDetail.value = false

            }
        }

        override fun onAcceptCargoBtnClick() {

//            _currentShipmentItemSelected.value = _currentShipmentItemSelected.value.copy(itemStatus = ShipmentItemStatus.SELECTED)

            /*            val updatedList = _shipmentList.value.items.map {
                            if (it == _currentShipmentItemSelected.value) {
                                it.copy(itemStatus = ShipmentItemStatus.SELECTED)
                            } else {
                                it.copy(itemStatus = ShipmentItemStatus.LOCKED)
                            }
                        }*/

            _cargoList.value = _cargoList.value.updateSelectionStatus(_currentCargoSelected.value)

            this@CargoViewModel.onDismissBottomSheet()
        }

        override fun onDismissRequestBottomSheet() {
            this@CargoViewModel.onDismissBottomSheet()
        }


        override fun onCancelCargoTextBtnClick() {
            val updatedList = _cargoList.value.items.map {
                it.copy(itemStatus = CargoStatus.NONE)
            }
            _cargoList.value = CargoResponseList(updatedList)

        }

    }


    fun getShipmentList() {
        viewModelScope.launch {
            repository.getMockCargoList()
                .apiResultCollector(states.getCargoListApiCallState) {
                    _cargoList.value = CargoResponseList(it)
                    _isLoadingCargoList.value = false
                }
        }
    }


    private fun onDismissBottomSheet() {
        _showCargoDetailBottomSheet.value = false
    }


}
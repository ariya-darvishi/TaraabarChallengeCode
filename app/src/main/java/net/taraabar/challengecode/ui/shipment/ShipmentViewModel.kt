package net.taraabar.challengecode.ui.shipment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.taraabar.challengecode.data.remote.model.response.ShipmentResponse
import net.taraabar.challengecode.data.remote.model.response.ShipmentResponseList
import net.taraabar.challengecode.data.repository.ITaraabarRepository
import net.taraabar.challengecode.utils.ShipmentItemStatus
import net.taraabar.network.states.apiResultCollector
import javax.inject.Inject


@HiltViewModel
class ShipmentViewModel @Inject constructor(
    private val repository: ITaraabarRepository,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _shipmentList = MutableStateFlow(ShipmentResponseList(emptyList()))
    private val _currentShipmentItemSelected = MutableStateFlow(ShipmentResponse())
    private val _isLoadingShipmentList = MutableStateFlow(false)
    private val _isLoadingShipmentItemDetail = MutableStateFlow(false)
    private val _showItemDetailBottomSheet = MutableStateFlow(false)

    val states = ShipmentStateHolder(
        shipmentList = _shipmentList.asStateFlow(),
        currentShipmentItemSelected = _currentShipmentItemSelected.asStateFlow(),
        isLoadingShipmentList = _isLoadingShipmentList.asStateFlow(),
        isLoadingShipmentItemDetail = _isLoadingShipmentItemDetail.asStateFlow(),
        showItemDetailBottomSheet = _showItemDetailBottomSheet.asStateFlow(),
    )

    val events = object : ShipmentEvents {

        override fun initShipmentScreen() {
            viewModelScope.launch {
                _isLoadingShipmentList.value = true
                delay(1000)
                getShipmentList()
            }

        }

        override fun onShipmentItemClick(item: ShipmentResponse) {
            _currentShipmentItemSelected.value = item
            _showItemDetailBottomSheet.value = true
            viewModelScope.launch {
                _isLoadingShipmentItemDetail.value = true
                delay(1000)
                _isLoadingShipmentItemDetail.value = false

            }
        }

        override fun onAcceptShipmentBtnClick() {

//            _currentShipmentItemSelected.value = _currentShipmentItemSelected.value.copy(itemStatus = ShipmentItemStatus.SELECTED)

            val updatedList = _shipmentList.value.items.map {
                if (it == _currentShipmentItemSelected.value) {
                    it.copy(itemStatus = ShipmentItemStatus.SELECTED)
                } else {
                    it.copy(itemStatus = ShipmentItemStatus.LOCKED)
                }
            }

            _shipmentList.value = ShipmentResponseList(updatedList)

            this@ShipmentViewModel.onDismissBottomSheet()
        }

        override fun onDismissRequestBottomSheet() {
            this@ShipmentViewModel.onDismissBottomSheet()
        }


        override fun onCancelShipmentTextBtnClick() {
            val updatedList = _shipmentList.value.items.map {
                it.copy(itemStatus = ShipmentItemStatus.NONE)
            }
            _shipmentList.value = ShipmentResponseList(updatedList)

        }

    }


    fun getShipmentList() {
        viewModelScope.launch {
            repository.getMockShipmentList()
                .apiResultCollector(states.getShipmentListApiCallState) {
                    _shipmentList.value = ShipmentResponseList(it)
                    _isLoadingShipmentList.value = false
                }
        }
    }


    private fun onDismissBottomSheet() {
        _showItemDetailBottomSheet.value = false
    }


}
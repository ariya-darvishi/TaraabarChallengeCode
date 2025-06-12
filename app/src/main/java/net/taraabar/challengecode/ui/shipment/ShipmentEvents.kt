package net.taraabar.challengecode.ui.shipment

import net.taraabar.challengecode.data.remote.model.response.ShipmentResponse

interface ShipmentEvents {
    fun initShipmentScreen()
    fun onDismissRequestBottomSheet()
    fun onShipmentItemClick(item: ShipmentResponse)
    fun onAcceptShipmentBtnClick()
    fun onCancelShipmentTextBtnClick()
}

val defaultShipmentEvents = object : ShipmentEvents {
    override fun initShipmentScreen() {}
    override fun onDismissRequestBottomSheet() {}
    override fun onShipmentItemClick(item: ShipmentResponse) {}
    override fun onAcceptShipmentBtnClick() {}
    override fun onCancelShipmentTextBtnClick() {}
}
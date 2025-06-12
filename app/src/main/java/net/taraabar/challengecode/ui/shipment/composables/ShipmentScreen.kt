package net.taraabar.challengecode.ui.shipment.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.taraabar.challengecode.ui.shipment.ShipmentViewModel

@Composable
fun ShipmentScreen(
    viewModel: ShipmentViewModel = hiltViewModel()
) {

    val events = remember { viewModel.events }

    //MainScreen
    val shipmentList by viewModel.states.shipmentList.collectAsStateWithLifecycle()
    val currentShipmentItemSelected by viewModel.states.currentShipmentItemSelected.collectAsStateWithLifecycle()
    val isLoadingShipmentList by viewModel.states.isLoadingShipmentList.collectAsStateWithLifecycle()

    //BottomSheet
    val showItemDetailBottomSheet by viewModel.states.showItemDetailBottomSheet.collectAsStateWithLifecycle()
    val isLoadingShipmentItemDetail by viewModel.states.isLoadingShipmentItemDetail.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        events.initShipmentScreen()
    }


}

@Composable
fun ShipmentScreenContent() {

}


@Preview
@Composable
fun ShipmentScreenPreview() {
    ShipmentScreenContent()

}
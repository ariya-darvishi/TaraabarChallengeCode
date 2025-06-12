package net.taraabar.challengecode.ui.cargo.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseList
import net.taraabar.challengecode.data.remote.model.response.CargoResponseListMockData
import net.taraabar.challengecode.ui.cargo.CargoViewModel
import net.taraabar.challengecode.ui.cargo.composables.cargoDetailBottomSheet.CargoDetailBottomSheetContent
import net.taraabar.designsystem.components.bottomSheet.TaraabarBottomSheet

@Composable
fun CargoScreen(
    viewModel: CargoViewModel = hiltViewModel()
) {

    val events = remember { viewModel.events }

    //MainScreen
    val cargoList by viewModel.states.cargoList.collectAsStateWithLifecycle()
    val currentCargoItemSelected by viewModel.states.currentCargoSelected.collectAsStateWithLifecycle()
    val isLoadingCargoList by viewModel.states.isLoadingCargoList.collectAsStateWithLifecycle()

    //BottomSheet
    val showCargoDetailBottomSheet by viewModel.states.showCargoDetailBottomSheet.collectAsStateWithLifecycle()
    val isLoadingCargoItemDetail by viewModel.states.isLoadingCargoItemDetail.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        events.initCargoScreen()
    }

    TaraabarBottomSheet(
        isVisible = showCargoDetailBottomSheet,
        showDragHandle = false,
        onDismiss = events::onDismissRequestBottomSheet
    ) {
        currentCargoItemSelected?.let {
            CargoDetailBottomSheetContent(
                isLoading = isLoadingCargoItemDetail,
                item = it,
                onDismiss = events::onDismissRequestBottomSheet,
                onAcceptCargoBtnClick = events::onAcceptCargoBtnClick,
            )
        }
    }


    CargoScreenContent(
        cargoList = cargoList,
        currentCargoItemSelected = currentCargoItemSelected,
        isLoadingCargoList = isLoadingCargoList,
    )

}

@Composable
fun CargoScreenContent(
    cargoList: CargoResponseList,
    currentCargoItemSelected: CargoResponse?,
    isLoadingCargoList: Boolean
) {

}


@Preview
@Composable
fun CargoScreenPreview() {
    CargoScreenContent(
        cargoList = CargoResponseListMockData.MOCK_DATA,
        currentCargoItemSelected = null,
        isLoadingCargoList = false,
    )

}
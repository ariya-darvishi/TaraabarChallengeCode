package net.taraabar.challengecode.ui.cargo.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseList
import net.taraabar.challengecode.data.remote.model.response.CargoResponseListMockData
import net.taraabar.challengecode.ui.cargo.CargoViewModel
import net.taraabar.challengecode.ui.cargo.composables.cargoDetailBottomSheet.CargoDetailBottomSheetContent
import net.taraabar.designsystem.R
import net.taraabar.designsystem.components.bottomSheet.TaraabarBottomSheet
import net.taraabar.designsystem.components.icon.ClickableIconWithRippleShape
import net.taraabar.designsystem.components.icon.DefaultClickableIcon
import net.taraabar.designsystem.components.toolbar.CustomToolbar
import net.taraabar.designsystem.theme.Neutral_900
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme
import net.taraabar.designsystem.theme.background
import net.taraabar.designsystem.theme.getFontFamilyYekanBakh
import net.taraabar.designsystem.utils.CARGO_LIST

@Composable
fun CargoScreen(
    viewModel: CargoViewModel = hiltViewModel()
) {

    val events = remember { viewModel.events }

    //MainScreen
    val cargoList by viewModel.states.cargoList.collectAsStateWithLifecycle()
    val currentCargoItemSelected by viewModel.states.currentCargoSelected.collectAsStateWithLifecycle()
    val isLoadingCargoList by viewModel.states.isLoadingCargoList.collectAsStateWithLifecycle()
    val hasMoreData by viewModel.states.hasMoreData.collectAsStateWithLifecycle()

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
        onCargoItemClick = events::onCargoItemClick,
        onCancelCargoTextBtnClick = events::onCancelCargoTextBtnClick,
        isLoadingCargoList = isLoadingCargoList,
        hasMoreData = hasMoreData,
        loadMore = events::loadMore,
    )

}

@Composable
fun CargoScreenContent(
    cargoList: CargoResponseList,
    isLoadingCargoList: Boolean,
    hasMoreData: Boolean,
    onCargoItemClick: (CargoResponse) -> Unit,
    onCancelCargoTextBtnClick: () -> Unit,
    loadMore: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Toolbar()

        CargoList(
            list = cargoList,
            isLoading = isLoadingCargoList,
            hasMoreData = hasMoreData,
            onItemClick = onCargoItemClick,
            onCancelCargoTextBtnClick = onCancelCargoTextBtnClick,
            loadMoreItems = loadMore
        )

    }
}


@Composable
fun Toolbar() {
    CustomToolbar(
        onBackClick = {},
        leadingIcon = {
            ClickableIconWithRippleShape(
                modifier = Modifier.padding(start = 12.dp),
                onClick = {},
                icon = { DefaultClickableIcon(R.drawable.ic_right_arrow) }
            )
        },
        title = {
            Text(
                text = CARGO_LIST,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelMedium,
                lineHeight = 20.sp,
                color = Neutral_900,
                fontFamily = getFontFamilyYekanBakh()
            )
        },
        trailingIcon = {
            ClickableIconWithRippleShape(
                modifier = Modifier.padding(end = 12.dp),
                onClick = {},
                icon = { DefaultClickableIcon(R.drawable.ic_message_question) }
            )
        }
    )
}


@Preview
@Composable
fun CargoScreenPreview() {
    TaraabarChallengeCodeTheme {
        CargoScreenContent(
            cargoList = CargoResponseListMockData.MOCK_DATA,
            isLoadingCargoList = false,
            hasMoreData = false,
            onCargoItemClick = {},
            onCancelCargoTextBtnClick = {},
            loadMore = {},
        )
    }
}
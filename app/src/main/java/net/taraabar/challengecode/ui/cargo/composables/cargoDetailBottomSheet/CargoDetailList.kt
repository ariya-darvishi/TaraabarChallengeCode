package net.taraabar.challengecode.ui.cargo.composables.cargoDetailBottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.taraabar.challengecode.data.remote.model.request.KeyValueModelList
import net.taraabar.challengecode.data.remote.model.request.KeyValueModelListMockData
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme


@Composable
fun CargoDetailList(
    list: KeyValueModelList,
    isLoading: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        if (isLoading) {
            repeat(6) {
                ShimmerCargoListItem(
                    showLine = true,
                )
            }
        } else {
            list.items.forEachIndexed() { index, item ->
                CargoListItem(
                    item = item,
                    showLine = if (index != list.items.size - 1) true else false,
                )
            }
        }
    }

}


@Preview(showBackground = true, heightDp = 600)
@Composable
fun CargoDetailListPreView() {
    TaraabarChallengeCodeTheme {
        CargoDetailList(
            list = KeyValueModelListMockData.MOCK_DATA,
            isLoading = false,
        )
    }
}


@Preview(showBackground = true, heightDp = 600)
@Composable
fun IpListPreView_IsLoading_TRUE() {
    TaraabarChallengeCodeTheme {
        CargoDetailList(
            list = KeyValueModelListMockData.MOCK_DATA,
            isLoading = true,
        )
    }
}
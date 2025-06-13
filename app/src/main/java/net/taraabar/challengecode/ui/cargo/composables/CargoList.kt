package net.taraabar.challengecode.ui.cargo.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseList
import net.taraabar.challengecode.data.remote.model.response.CargoResponseListMockData
import net.taraabar.designsystem.utils.PAGE_MAX_SIZE
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme


@OptIn(FlowPreview::class)
@Composable
fun CargoList(
    list: CargoResponseList,
    isLoading: Boolean,
    hasMoreData: Boolean,
    onClick: (CargoResponse) -> Unit,
    onCancelCargoTextBtnClick: () -> Unit,
    loadMoreItems: () -> Unit,
) {
    val listState: LazyListState = rememberLazyListState()
    val buffer = 3

    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            if (totalItemsCount < PAGE_MAX_SIZE || !hasMoreData)
                false
            else {
                val lastVisibleItemIndex =
                    listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastVisibleItemIndex >= (totalItemsCount - buffer) && !isLoading
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .debounce(300) // جلوگیری از اجرای مکرر بارگذاری
            .filter { it }
            .collect { loadMoreItems() }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(list.items) { item ->
            CargoListItem(
                item = item,
                onClick = onClick,
                onCancelCargoTextBtnClick = onCancelCargoTextBtnClick,
            )
        }

        // آیتم بارگذاری در انتهای لیست
        if (isLoading && hasMoreData) {
            item {
                repeat(10) {
                    ShimmerCargoListItem()
                }
            }
        }
    }
}


@Preview(showBackground = true, heightDp = 600)
@Composable
fun CargoListPreView() {
    TaraabarChallengeCodeTheme {
        CargoList(
            list = CargoResponseListMockData.MOCK_DATA,
            isLoading = false,
            hasMoreData = true,
            onClick = {},
            loadMoreItems = {},
            onCancelCargoTextBtnClick = {},

            )
    }
}


@Preview(showBackground = true, heightDp = 600)
@Composable
fun CargoListPreView_IsLoading_TRUE() {
    TaraabarChallengeCodeTheme {
        CargoList(
            list = CargoResponseListMockData.MOCK_DATA,
            isLoading = true,
            hasMoreData = false,
            onClick = {},
            loadMoreItems = {},
            onCancelCargoTextBtnClick = {}
        )
    }
}
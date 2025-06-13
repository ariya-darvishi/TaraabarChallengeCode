package net.taraabar.challengecode.ui.cargo.composables

import android.util.Log
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
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme

@OptIn(FlowPreview::class)
@Composable
fun CargoList(
    list: CargoResponseList,
    isLoading: Boolean,
    hasMoreData: Boolean,
    onItemClick: (CargoResponse) -> Unit,
    onCancelCargoTextBtnClick: () -> Unit,
    loadMoreItems: () -> Unit,
) {
    val listState: LazyListState = rememberLazyListState()
    val buffer = 3

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list.items, key = { it.amount.orEmpty() }) { item ->
            CargoListItem(
                item = item,
                onItemClick = onItemClick,
                onCancelCargoTextBtnClick = onCancelCargoTextBtnClick
            )
        }

        if (isLoading) {
            items(5) {
                ShimmerCargoListItem()
            }
        }
    }

    val shouldLoadMore = remember {
        derivedStateOf {
            if (!hasMoreData || isLoading) {
                Log.d(
                    "CargoList",
                    "shouldLoadMore: false (hasMoreData=$hasMoreData, isLoading=$isLoading)"
                )
                return@derivedStateOf false
            }

            val layoutInfo = listState.layoutInfo
            val totalItemsCount = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val result = lastVisibleItemIndex >= totalItemsCount - buffer
            Log.d(
                "CargoList",
                "shouldLoadMore: $result (lastVisibleItemIndex=$lastVisibleItemIndex, totalItemsCount=$totalItemsCount)"
            )
            result
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .debounce(300)
            .filter { it }
            .collect {
                Log.d("CargoList", "Load more triggered")
                loadMoreItems()
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
            onItemClick = {},
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
            onItemClick = {},
            loadMoreItems = {},
            onCancelCargoTextBtnClick = {}
        )
    }
}
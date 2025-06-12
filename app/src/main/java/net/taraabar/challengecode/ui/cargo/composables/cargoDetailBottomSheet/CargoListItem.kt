package net.taraabar.challengecode.ui.cargo.composables.cargoDetailBottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import net.taraabar.challengecode.data.remote.model.request.KeyValueModel
import net.taraabar.challengecode.data.remote.model.request.KeyValueModelListMockData
import net.taraabar.designsystem.components.divider.LinearDivider
import net.taraabar.designsystem.components.loading.ShimmerBox
import net.taraabar.designsystem.components.loading.shimmerTheme
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme


@Composable
fun CargoListItem(
    item: KeyValueModel,
    showLine: Boolean = true,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "${item.key}: ",
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = item.value,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start,
        )

    }


    if (showLine)
        LinearDivider(
            thickness = 1.dp,
            color = Color.Black.copy(alpha = 0.1f),
            modifier = Modifier.padding(vertical = 5.dp)
        )
}


@Composable
fun ShimmerCargoListItem(
    showLine: Boolean = true,
) {
    val shimmerInstance =
        rememberShimmer(shimmerBounds = ShimmerBounds.Window, theme = shimmerTheme())


    Row(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .fillMaxWidth()
            .padding(12.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ShimmerBox(height = 20.dp, width = 50.dp)


        Spacer(modifier = Modifier.weight(1f))

        ShimmerBox(height = 20.dp, width = 50.dp)


    }
    if (showLine)
        LinearDivider(
            thickness = 1.dp,
            color = Color.Black.copy(alpha = 0.1f),
            modifier = Modifier.padding(vertical = 5.dp)
        )

    ShimmerBox(height = 20.dp, width = 50.dp)
}


@Preview(showBackground = true, heightDp = 50)
@Composable
fun CargoListItemPreView() {
    TaraabarChallengeCodeTheme {
        CargoListItem(
            item = KeyValueModelListMockData.MOCK_DATA.items.first(),
        )
    }
}


@Preview(showBackground = true, heightDp = 50)
@Composable
fun CargoListItemPreView_Loading_TRUE() {
    TaraabarChallengeCodeTheme {
        ShimmerCargoListItem()
    }
}

package net.taraabar.challengecode.ui.cargo.composables.cargoDetailBottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import net.taraabar.designsystem.theme.getFontFamilyYekanBakh


@Composable
fun CargoDetailListItem(
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
            fontFamily = getFontFamilyYekanBakh(),
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = item.value,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start,
            fontFamily = getFontFamilyYekanBakh()
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
fun ShimmerCargoDetailListItem() {
    val shimmerInstance =
        rememberShimmer(shimmerBounds = ShimmerBounds.Window, theme = shimmerTheme())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shimmer(shimmerInstance)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerBox(height = 18.dp, width = 50.dp)
            Spacer(modifier = Modifier.weight(1f))
            ShimmerBox(height = 18.dp, width = 50.dp)

        }
        Spacer(modifier = Modifier.height(5.dp))

        LinearDivider(
            thickness = 1.dp,
            color = Color.Black.copy(alpha = 0.1f),
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

    }
}


@Preview(showBackground = true, heightDp = 50)
@Composable
fun CargoDetailListItemPreView() {
    TaraabarChallengeCodeTheme {
        CargoDetailListItem(
            item = KeyValueModelListMockData.MOCK_DATA.items.first(),
        )
    }
}


@Preview(showBackground = true, heightDp = 250)
@Composable
fun CargoDetailListItemPreView_Loading_TRUE() {
    TaraabarChallengeCodeTheme {
        ShimmerCargoDetailListItem()
    }
}

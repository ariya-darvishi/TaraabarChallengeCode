package net.taraabar.challengecode.ui.cargo.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import net.taraabar.challengecode.R
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseListMockData
import net.taraabar.challengecode.utils.CargoStatus
import net.taraabar.designsystem.components.buttons.CustomTextButton
import net.taraabar.designsystem.components.divider.LinearDivider
import net.taraabar.designsystem.components.loading.ShimmerBox
import net.taraabar.designsystem.components.loading.shimmerTheme
import net.taraabar.designsystem.components.shape.Ellipse
import net.taraabar.designsystem.components.shape.RoundedBlackBox
import net.taraabar.designsystem.theme.Neutral_100
import net.taraabar.designsystem.theme.Neutral_80
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme
import net.taraabar.designsystem.theme.black
import net.taraabar.designsystem.theme.getFontFamilyYekanBakh
import net.taraabar.designsystem.theme.gray
import net.taraabar.designsystem.utils.CANCEL_CARGO
import net.taraabar.designsystem.utils.DESTINATION
import net.taraabar.designsystem.utils.DividerType
import net.taraabar.designsystem.utils.ORIGIN
import net.taraabar.designsystem.utils.priceStyle
import net.taraabar.designsystem.utils.toPersianNumber


@Composable
fun CargoListItem(
    item: CargoResponse,
    onItemClick: (CargoResponse) -> Unit,
    onCancelCargoTextBtnClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                if (item.itemStatus == CargoStatus.NONE)
                    onItemClick.invoke(item)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
    ) {
        Column(
            Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            if (item.itemStatus == CargoStatus.SELECTED)
                SelectedCargoLabelLayout(
                    item = item,
                    onCancelCargoTextBtnClick = onCancelCargoTextBtnClick
                )

            SelectedRouteCardLayout(item = item)

            LinearDivider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 13.dp),
                color = Color.Black.copy(alpha = 0.1f),
            )

            CargoInfoLayout(item = item)

        }
    }

}


@Composable
fun SelectedCargoLabelLayout(
    item: CargoResponse,
    onCancelCargoTextBtnClick: () -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(gray),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = "بار ${item.destinationProvince} به ${item.originProvince} انتخاب شده است",
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral_100,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(0.6f),
                fontFamily = getFontFamilyYekanBakh()
            )

            CustomTextButton(
                onBtnClick = onCancelCargoTextBtnClick,
                enabled = true,
                modifier = Modifier.height(24.dp),
                text = {
                    Text(
                        text = CANCEL_CARGO,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = net.taraabar.designsystem.theme.error,
                        fontFamily = getFontFamilyYekanBakh()
                    )
                }
            )
        }
    }
    Spacer(Modifier.height(12.dp))

}

@Composable
fun CargoInfoLayout(
    item: CargoResponse,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_weight),
            contentDescription = "",
            tint = Color.Unspecified
        )
        Spacer(Modifier.width(4.dp))

        Text(
            text = "${item.weight.orEmpty().toPersianNumber()} ${item.weightUnit}",
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Neutral_100,
            fontFamily = getFontFamilyYekanBakh()
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = "${item.amount.orEmpty().priceStyle()} ${item.amountUnit}",
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
            color = black,
            fontFamily = getFontFamilyYekanBakh()
        )
    }
}


@Composable
fun SelectedRouteCardLayout(
    item: CargoResponse,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Ellipse(size = 7.5.dp)
            Spacer(Modifier.width(12.dp))
            Text(
                text = ORIGIN,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral_100,
                fontFamily = getFontFamilyYekanBakh()
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = "(استان ${item.originProvince})",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral_80,
                fontFamily = getFontFamilyYekanBakh()
            )
            Spacer(Modifier.weight(1f))

            if (item.itemStatus == CargoStatus.LOCKED)
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock),
                    contentDescription = "",
                    tint = Color.Unspecified
                )

        }

        LinearDivider(
            thickness = 1.dp,
            modifier = Modifier
                .height(22.dp)
                .padding(horizontal = 3.dp),
            color = Color.Black.copy(alpha = 0.1f),
            dividerType = DividerType.VERTICAL
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            RoundedBlackBox()
            Spacer(Modifier.width(12.dp))
            Text(
                text = DESTINATION,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral_100,
                fontFamily = getFontFamilyYekanBakh()
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = "(استان ${item.destinationProvince})",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral_80,
                fontFamily = getFontFamilyYekanBakh()
            )

        }
    }
}


@Composable
fun ShimmerCargoListItem() {
    val shimmerInstance =
        rememberShimmer(shimmerBounds = ShimmerBounds.Window, theme = shimmerTheme())


    Card(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
    ) {
        Column(
            Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    ShimmerBox(height = 7.5.dp, width = 7.5.dp)
                    Spacer(Modifier.width(12.dp))
                    ShimmerBox(height = 18.dp, width = 50.dp)

                    Spacer(Modifier.width(4.dp))

                    ShimmerBox(height = 18.dp, width = 50.dp)

                    Spacer(Modifier.weight(1f))


                }

                LinearDivider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .height(22.dp)
                        .padding(horizontal = 3.dp),
                    color = Color.Black.copy(alpha = 0.1f),
                    dividerType = DividerType.VERTICAL
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    ShimmerBox(height = 8.dp, width = 8.dp, cornerRadius = RoundedCornerShape(8.dp))
                    Spacer(Modifier.width(12.dp))
                    ShimmerBox(height = 18.dp, width = 50.dp)

                    Spacer(Modifier.width(4.dp))

                    ShimmerBox(height = 18.dp, width = 50.dp)

                    Spacer(Modifier.weight(1f))


                }
            }

            LinearDivider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 13.dp),
                color = Color.Black.copy(alpha = 0.1f),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ShimmerBox(height = 20.dp, width = 20.dp)

                Spacer(Modifier.width(4.dp))

                ShimmerBox(height = 18.dp, width = 60.dp)


                Spacer(Modifier.weight(1f))

                ShimmerBox(height = 18.dp, width = 70.dp)

            }
        }
    }
    Spacer(Modifier.height(12.dp))

}


@Preview(showBackground = true, heightDp = 170)
@Composable
fun CargoListItemPreView() {
    TaraabarChallengeCodeTheme {
        CargoListItem(
            item = CargoResponseListMockData.MOCK_DATA.items.first(),
            onItemClick = {},
            onCancelCargoTextBtnClick = {}
        )
    }
}

@Preview(showBackground = true, heightDp = 270)
@Composable
fun CargoListItemPreView_StatusType_SELECTED() {
    TaraabarChallengeCodeTheme {
        CargoListItem(
            item = CargoResponseListMockData.MOCK_DATA.items.first()
                .copy(itemStatus = CargoStatus.SELECTED),
            onItemClick = {},
            onCancelCargoTextBtnClick = {}
        )
    }
}

@Preview(showBackground = true, heightDp = 170)
@Composable
fun CargoListItemPreView_StatusType_LOCKED() {
    TaraabarChallengeCodeTheme {
        CargoListItem(
            item = CargoResponseListMockData.MOCK_DATA.items.first()
                .copy(itemStatus = CargoStatus.LOCKED),
            onItemClick = {},
            onCancelCargoTextBtnClick = {}
        )
    }
}


@Preview(showBackground = true, heightDp = 170)
@Composable
fun CargoListItemPreView_Loading_TRUE() {
    TaraabarChallengeCodeTheme {
        ShimmerCargoListItem()
    }
}

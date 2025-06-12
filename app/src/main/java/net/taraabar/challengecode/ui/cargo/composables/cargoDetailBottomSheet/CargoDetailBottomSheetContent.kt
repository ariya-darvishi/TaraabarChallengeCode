package net.taraabar.challengecode.ui.cargo.composables.cargoDetailBottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.taraabar.challengecode.R
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseListMockData
import net.taraabar.challengecode.utils.CARGO_DETAIL
import net.taraabar.challengecode.utils.toKeyValueModelList
import net.taraabar.designsystem.components.buttons.CustomFilledButton
import net.taraabar.designsystem.components.divider.LinearDivider
import net.taraabar.designsystem.components.icon.ClickableIconWithRippleShape
import net.taraabar.designsystem.components.icon.DefaultClickableIcon
import net.taraabar.designsystem.theme.Neutral_0
import net.taraabar.designsystem.theme.Neutral_900
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme
import net.taraabar.designsystem.theme.gray
import net.taraabar.designsystem.theme.primary
import net.taraabar.designsystem.utils.priceStyle

@Composable
fun CargoDetailBottomSheetContent(
    isLoading: Boolean = false,
    item: CargoResponse,
    onDismiss: () -> Unit,
    onAcceptCargoBtnClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderLayout(onDismiss = onDismiss)


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CargoDetailList(
                list = item.toKeyValueModelList(),
                isLoading = isLoading,
            )
        }

        AcceptButtonLayout(
            onAcceptCargoBtnClick = onAcceptCargoBtnClick,
            amount = "${item.amount}".priceStyle()
        )
    }

}


@Composable
fun HeaderLayout(
    onDismiss: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = CARGO_DETAIL,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Neutral_900,
        )

        Spacer(modifier = Modifier.weight(1f))

        ClickableIconWithRippleShape(
            onClick = onDismiss,
            icon = { DefaultClickableIcon(R.drawable.ic_close) }
        )

    }


    LinearDivider(
        thickness = 1.dp,
        color = Color.Black.copy(alpha = 0.1f),
        modifier = Modifier.padding(vertical = 5.dp)
    )
}


@Composable
fun AcceptButtonLayout(
    onAcceptCargoBtnClick: () -> Unit,
    amount: String
) {

    LinearDivider(
        thickness = 1.dp,
        color = Color.Black.copy(alpha = 0.1f),
        modifier = Modifier.padding(vertical = 18.dp)
    )

    CustomFilledButton(
        onBtnClick = onAcceptCargoBtnClick,
        enabled = true,
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonColors(
            containerColor = primary,
            contentColor = Neutral_0,
            disabledContainerColor = gray,
            disabledContentColor = Neutral_0,

            ),
        text = {
            Text(
                text = "با  $amount میلیون تومان کرایه می\u200Cبرم",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 16.sp,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun IpListBottomSheetPreview() {
    TaraabarChallengeCodeTheme {
        CargoDetailBottomSheetContent(
            isLoading = false,
            item = CargoResponseListMockData.MOCK_DATA.items.first(),
            onDismiss = {},
            onAcceptCargoBtnClick = {},
        )
    }
}


@Preview(showBackground = true)
@Composable
fun IpListBottomSheetLoadingPreview() {
    TaraabarChallengeCodeTheme {
        CargoDetailBottomSheetContent(
            isLoading = true,
            item = CargoResponseListMockData.MOCK_DATA.items.first(),
            onDismiss = {},
            onAcceptCargoBtnClick = {},
        )
    }
}



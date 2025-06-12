package net.taraabar.designsystem.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import net.taraabar.designsystem.components.loading.shimmerTheme
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme
import net.taraabar.designsystem.theme.gray


@Composable
fun CustomFilledButton(
    modifier: Modifier = Modifier,
    onBtnClick: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    colors: ButtonColors? = null,
    shape: Shape? = CircleShape,
    icon: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
) {
    if (isLoading)
        ShimmerCustomFilledButton()
    else
        FilledTonalButton(
            enabled = enabled,
            onClick = onBtnClick,
            modifier = Modifier
                .height(50.dp)
                .then(modifier),
            colors = colors
                ?: ButtonDefaults.filledTonalButtonColors().copy(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = gray.copy(alpha = 0.2f),
                    disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                ),
            shape = shape!!
        ) {

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                icon?.invoke()

                if (icon != null && text != null)
                    Spacer(modifier = Modifier.width(12.dp))

                text?.invoke()

            }
        }
}


@Composable
fun ShimmerCustomFilledButton() {
    val shimmerInstance =
        rememberShimmer(shimmerBounds = ShimmerBounds.Window, theme = shimmerTheme())
    Box(modifier = Modifier.shimmer(shimmerInstance)) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary)
        )
    }

}

@Preview(heightDp = 50, widthDp = 150)
@Composable
fun ButtonPreview() {
    TaraabarChallengeCodeTheme {
        CustomFilledButton(
            onBtnClick = {},
            enabled = true,
            text = {
                Text(
                    text = "تایید و ادامه",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 16.sp,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                )
            }
        )
    }
}


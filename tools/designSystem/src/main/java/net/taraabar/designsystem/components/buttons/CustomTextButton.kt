package net.taraabar.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.taraabar.designsystem.R
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme
import net.taraabar.designsystem.theme.getFontFamilyYekanBakh


@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    onBtnClick: () -> Unit,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
) {
    TextButton(
        enabled = enabled,
        onClick = onBtnClick,
        modifier = Modifier
            .height(50.dp)
            .then(modifier),
        colors = ButtonDefaults.filledTonalButtonColors().copy(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        ),
        shape = CircleShape
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


@Preview(heightDp = 50, widthDp = 150)
@Composable
fun CustomTextButtonPreview() {
    TaraabarChallengeCodeTheme {
        CustomTextButton(
            onBtnClick = {},
            enabled = true,
            text = {
                Text(
                    text = "بازیابی رمز عبور",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 16.sp,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                    fontFamily = getFontFamilyYekanBakh()
                )
            }
        )
    }
}


@Preview(heightDp = 50, widthDp = 150)
@Composable
fun CustomTextButtonPreview_Disable() {
    TaraabarChallengeCodeTheme {
        CustomTextButton(
            onBtnClick = {},
            enabled = false,
            text = {
                Text(
                    text = "بازیابی رمز عبور",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 16.sp,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                    fontFamily = getFontFamilyYekanBakh()
                )
            }
        )
    }
}


@Preview(heightDp = 50, widthDp = 70)
@Composable
fun CustomTextButtonPreview_JustIcon() {
    TaraabarChallengeCodeTheme {
        CustomTextButton(
            onBtnClick = {},
            enabled = true,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "button icon",
                    modifier = Modifier.size(12.dp)
                )
            },
            modifier = Modifier.wrapContentWidth()
        )
    }
}


@Preview(heightDp = 50, widthDp = 70)
@Composable
fun CustomTextButtonPreview_JustIconDisable() {
    TaraabarChallengeCodeTheme {
        CustomTextButton(
            onBtnClick = {},
            enabled = false,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "button icon",
                    modifier = Modifier.size(12.dp)
                )
            },
            modifier = Modifier.wrapContentWidth()
        )
    }
}


@Preview(heightDp = 50, widthDp = 160)
@Composable
fun CustomTextButtonPreview_withIcon() {
    TaraabarChallengeCodeTheme {
        CustomTextButton(
            onBtnClick = {},
            enabled = true,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "button icon",
                    modifier = Modifier.size(12.dp)
                )
            },
            modifier = Modifier.wrapContentWidth(),
            text = {
                Text(
                    text = "بازیابی رمز عبور",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = getFontFamilyYekanBakh()
                )
            }
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF020202, heightDp = 50, widthDp = 160)
@Composable
fun CustomTextButtonPreview_withIconDisable() {
    TaraabarChallengeCodeTheme {
        CustomTextButton(
            onBtnClick = {},
            enabled = false,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "button icon",
                    modifier = Modifier.size(12.dp)
                )
            },
            modifier = Modifier.wrapContentWidth(),
            text = {
                Text(
                    text = "بازیابی رمز عبور",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 16.sp,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                    fontFamily = getFontFamilyYekanBakh()
                )
            }
        )
    }
}
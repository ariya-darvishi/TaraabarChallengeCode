package net.taraabar.designsystem.components.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.taraabar.designsystem.R
import net.taraabar.designsystem.components.icon.ClickableIconWithRippleShape
import net.taraabar.designsystem.components.icon.DefaultClickableIcon
import net.taraabar.designsystem.theme.Neutral_900
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme


@Composable
fun CustomToolbar(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit),
    description: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.onSecondary,
    onBackClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
            .background(backgroundColor)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        leadingIcon?.let {
            it.invoke()
            Spacer(modifier = Modifier.width(14.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            title.invoke()
            description?.invoke()
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            trailingIcon?.let {
                it.invoke()
                Spacer(modifier = Modifier.width(14.dp))
            }

            ClickableIconWithRippleShape(
                onClick = onBackClick,
                icon = { DefaultClickableIcon(R.drawable.ic_message_question) }
            )

        }
    }
}

@Preview(showBackground = true, heightDp = 58)
@Composable
fun CustomToolbarPreview() {
    TaraabarChallengeCodeTheme {
        CustomToolbar(
            onBackClick = {},
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            },
            title = {
                Text(
                    text = "خانه",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelMedium,
                    lineHeight = 16.sp,
                    color = Neutral_900,
                )
            },
        )
    }
}

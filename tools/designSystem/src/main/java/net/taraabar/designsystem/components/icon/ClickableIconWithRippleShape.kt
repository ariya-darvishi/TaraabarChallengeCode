package net.taraabar.designsystem.components.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.taraabar.designsystem.R

@Composable
fun ClickableIconWithRippleShape(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .then(modifier)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = remember { ripple() }
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        icon.invoke()
    }
}


@Composable
fun DefaultClickableIcon(
    drawableIconId: Int
) {
    Icon(
        modifier = Modifier.size(24.dp),
        imageVector = ImageVector.vectorResource(id = drawableIconId),
        contentDescription = "",
        tint = Color.Unspecified
    )
}


@Preview(showBackground = true, widthDp = 40, heightDp = 40)
@Composable
fun ClickableIconWithRippleShapePreview() {
    ClickableIconWithRippleShape(
        onClick = {},
        icon = { DefaultClickableIcon(R.drawable.ic_message_question) }
    )
}
package net.taraabar.designsystem.components.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.taraabar.designsystem.theme.Neutral_20
import net.taraabar.designsystem.utils.DividerType

@Composable
fun LinearDivider(
    modifier: Modifier = Modifier,
    dividerType: DividerType = DividerType.HORIZONTAL,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = Neutral_20
) {
    Box(modifier = modifier) {
        when (dividerType) {
            DividerType.HORIZONTAL ->
                HorizontalDivider(
                    modifier = Modifier
                        .background(color)
                        .fillMaxWidth()
                        .height(1.dp),
                    color = color,
                    thickness = thickness
                )

            DividerType.VERTICAL ->
                VerticalDivider(
                    modifier = Modifier
                        .background(color)
                        .fillMaxHeight()
                        .width(1.dp),
                    color = color,
                    thickness = thickness
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LinearDividerPreview_Horizontal() {
    LinearDivider(
        modifier = Modifier.fillMaxWidth(),
        dividerType = DividerType.HORIZONTAL,
        color = Neutral_20
    )
}

@Preview(showBackground = true)
@Composable
fun LinearDividerPreview_Vertical() {
    LinearDivider(
        modifier = Modifier.fillMaxWidth(),
        dividerType = DividerType.VERTICAL,
        color = Neutral_20
    )
}
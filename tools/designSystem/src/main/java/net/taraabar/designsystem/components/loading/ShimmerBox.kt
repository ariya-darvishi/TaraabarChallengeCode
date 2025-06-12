package net.taraabar.designsystem.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerBox(
    height: Dp = 20.dp,
    width: Dp = 20.dp,
    cornerRadius: RoundedCornerShape = RoundedCornerShape(12.dp),
    background: Color = MaterialTheme.colorScheme.secondary,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .height(height)
            .width(width)
            .clip(cornerRadius)
            .background(background)
    )
}


@Preview(showBackground = true)
@Composable
fun ShimmerBoxPreview() {
    ShimmerBox()
}
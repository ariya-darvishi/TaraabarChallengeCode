package net.taraabar.designsystem.components.text

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.taraabar.designsystem.theme.Neutral_100
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme


@Composable
fun Ellipse(size: Dp = 4.dp, color: Color = Neutral_100) {
    Canvas(
        modifier = Modifier
            .size(size),
        onDraw = {
            drawCircle(color)
        }
    )
}

@Preview(showBackground = true, widthDp = 10, heightDp = 10)
@Composable
fun EllipsePreview() {
    TaraabarChallengeCodeTheme {
        Ellipse()
    }
}


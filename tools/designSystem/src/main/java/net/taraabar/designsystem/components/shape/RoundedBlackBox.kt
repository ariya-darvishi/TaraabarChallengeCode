package net.taraabar.designsystem.components.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.taraabar.designsystem.theme.Neutral_100
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme

@Composable
fun RoundedBlackBox(size: Dp = 7.dp, cornerSize: Dp = 2.dp, color: Color = Neutral_100) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = color,
                shape = RoundedCornerShape(cornerSize)
            )
    )
}

@Preview(showBackground = true, widthDp = 60, heightDp = 60)
@Composable
fun RoundedBlackBoxPreview() {
    TaraabarChallengeCodeTheme {
        RoundedBlackBox()
    }
}

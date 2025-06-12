package net.taraabar.designsystem.components.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.tooling.preview.Preview
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.ShimmerTheme
import com.valentinilk.shimmer.shimmerSpec
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme

@Composable
@ReadOnlyComposable
fun shimmerTheme(): ShimmerTheme {
    return LocalShimmerTheme.current.copy(
        animationSpec = infiniteRepeatable(
            animation = shimmerSpec(
                durationMillis = 500,
                easing = LinearEasing,
                delayMillis = 500,
            ),
            repeatMode = RepeatMode.Restart,
        )
    )
}


@Preview
@Composable
fun ShimmerThemePreview() {
    TaraabarChallengeCodeTheme {
        shimmerTheme()
    }
}
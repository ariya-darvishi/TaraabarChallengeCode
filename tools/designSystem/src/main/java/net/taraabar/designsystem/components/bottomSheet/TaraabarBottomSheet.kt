package net.taraabar.designsystem.components.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme
import net.taraabar.designsystem.theme.black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaraabarBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    skipPartiallyExpanded: Boolean = true,
    showDragHandle: Boolean = true,
    shape: Shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
    content: @Composable () -> Unit
) {
    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded),
            dragHandle = {
                if (showDragHandle) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(black.copy(alpha = 0.6f)),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                } else null
            },
            containerColor = Color.White,
            tonalElevation = 8.dp,
            shape = shape,
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun VisibleBottomSheetPreview() {
    TaraabarChallengeCodeTheme() {
        TaraabarBottomSheet(
            isVisible = true,
            onDismiss = {}
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Bottom Sheet Content")
            }
        }
    }
}
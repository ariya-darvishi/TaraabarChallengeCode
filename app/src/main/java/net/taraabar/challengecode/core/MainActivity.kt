package net.taraabar.challengecode.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.taraabar.challengecode.ui.navigation.TaraabarMainNavigation
import net.taraabar.designsystem.theme.TaraabarChallengeCodeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MainActivityContent()
        }
    }
}


@Composable
fun MainActivityContent() {

    val navController = rememberNavController()

    TaraabarChallengeCodeTheme {
        TaraabarMainNavigation(
            navHostController = navController,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
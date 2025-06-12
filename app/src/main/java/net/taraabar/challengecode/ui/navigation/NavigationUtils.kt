package net.taraabar.challengecode.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.taraabar.challengecode.ui.cargo.composables.CargoScreen
import javax.inject.Inject

sealed class MainDestinations(val route: String) {
    data object Main : MainDestinations("main")
}


@Composable
fun TaraabarMainNavigation(
    modifier: Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = MainDestinations.Main.route
    ) {

        composable(route = MainDestinations.Main.route) { CargoScreen() }

    }
}


interface ITaraabarNavigation {
    val controller: NavHostController
    fun navigateToMain()
}

class TaraabarNavigation @Inject constructor(
    iNavControllerProvider: ITaraabarNavigation
) : ITaraabarNavigation {
    private var navController: NavHostController? = null
    override val controller: NavHostController
        get() = navController!!


    override fun navigateToMain() {
        controller.navigate(MainDestinations.Main.route)
    }
}

package com.example.nasaexplorer

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun NasaNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier,
    startDestination: String = Landing.route,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    NavHost(
        navController = navController, startDestination = Landing.route, modifier = modifier
    ) {
        composable(route = Landing.route) {
            Landing.screen(
                scope, snackbarHostState
            ) { navController.navigateSingleTopTo(AstronomyIOTD.route) }
        }
        composable(route = AstronomyIOTD.route) {
            AstronomyIOTD.screen(scope, snackbarHostState) {}
        }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }
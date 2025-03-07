package com.example.nasaexplorer

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.example.nasaexplorer.ui.Screens.AstronomyIOTDScreen
import com.example.nasaexplorer.ui.Screens.LandingScreen
import kotlinx.coroutines.CoroutineScope

interface NasaDestination {
    val route: String
    val screen: @Composable (CoroutineScope, SnackbarHostState, () -> Unit) -> Unit
}

object Landing : NasaDestination {
    override val route = "Landing"
    override val screen: @Composable (scope: CoroutineScope, snackbarHostState: SnackbarHostState, () -> Unit) -> Unit
        get() = { scope, snackbarHostState, navigateToAOTD ->
            LandingScreen(
                name = "Android", scope = scope, snackbarHostState = snackbarHostState, navigateToAOTD = navigateToAOTD
            )
        }
}

object AstronomyIOTD : NasaDestination {
    override val route = "AstronomyIOTD"
    override val screen: @Composable (scope: CoroutineScope, snackbarHostState: SnackbarHostState, () -> Unit) -> Unit
        get() = { scope, _, _ ->
            AstronomyIOTDScreen(
                name = "Android", scope = scope
            )
        }
}